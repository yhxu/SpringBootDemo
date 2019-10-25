package com.xuyh.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author xuyh
 * @Description: Excel导入，支持2007以上版本，文件后缀.xlsx的文档，支持数据量大的处理
 * @Date: 2018/2/5
 * @Version:
 */
public class ImpExcelBigData {

    /**
     * 读取xlsx文件
     * @param path 文件路径
     * @param sheetName sheet页名称
     * @return
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static List<List<String[]>> readerExcel(String path, String sheetName) throws IOException, OpenXML4JException, ParserConfigurationException, SAXException{
        return readerExcel(new File(path), sheetName);
    }

    /**
     * 读取xlsx文件
     * @param file File对象
     * @param sheetName sheet页名称
     * @return
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static List<List<String[]>> readerExcel(File file, String sheetName) throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        OPCPackage              opcPackage      = OPCPackage.open(file, PackageAccess.READ);
        List<List<String[]>>    excelDatelist   = process(opcPackage, sheetName);
        opcPackage.close();
        return excelDatelist;
    }
    private static List<List<String[]>> process(OPCPackage opcPackage, String sheetName) throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        XSSFReader                  xssfReader    = new XSSFReader(opcPackage);
        List<String[]>              sheetDatalist = new ArrayList<>();
        List<List<String[]>>        excelDataList = new ArrayList<>();
        ReadOnlySharedStringsTable  strings       = new ReadOnlySharedStringsTable(opcPackage);

        StylesTable              styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter   = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        while (iter.hasNext()) {
            InputStream stream          = iter.next();
            String      sheetNameTemp   = iter.getSheetName();
            if (null != sheetName && !"".equals(sheetName)) {
                if(sheetName.equals(sheetNameTemp)){
                    sheetDatalist = processSheet(styles, strings, stream);
                    stream.close();
                }
            } else {
                sheetDatalist = processSheet(styles, strings, stream);
                stream.close();
            }
            excelDataList.add(sheetDatalist);
        }
        return excelDataList;
    }
    private static List<String[]> processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, InputStream sheetInputStream) throws IOException, ParserConfigurationException, SAXException {
        InputSource        sheetSource  = new InputSource(sheetInputStream);
        MyXSSFSheetHandler handler      = new MyXSSFSheetHandler(styles, strings);

        SAXParserFactory   saxFactory   = SAXParserFactory.newInstance();
        SAXParser          saxParser    = saxFactory.newSAXParser();
        XMLReader          sheetParser  = saxParser.getXMLReader();
        handler.setDataFormat("yyyyMMdd");
        sheetParser.setContentHandler(handler);
        sheetParser.parse(sheetSource);
        return handler.getRows();
    }

    private static class MyXSSFSheetHandler extends DefaultHandler {

        enum XSSFDataType {
            BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
        }

        private String                      mDataTimeFormat   = "yyyyMMddHHmmss";
        private String                      mDataFormat       = "yyyy/MM/dd";
        private String                      mTimeFormat       = "HH:mm:ss";
        private ReadOnlySharedStringsTable  sharedStringsTable;
        private StylesTable                 stylesTable;
        private XSSFDataType                nextDataType;
        private DataFormatter formatter;
        private StringBuffer                value;
        private String                      formatString;
        private boolean                     vIsOpen;
        private short                       formatIndex;
        private boolean                     isCellNull          = false;
        private int                         thisColumn          = -1;
        private int                         lastColumnNumber    = -1;
        private List<String>    record = new ArrayList<>();
        private List<String[]>  rows   = new ArrayList<>();


        public MyXSSFSheetHandler(StylesTable styles, ReadOnlySharedStringsTable strings) {
            this.sharedStringsTable = strings;
            this.stylesTable        = styles;
            this.nextDataType       = XSSFDataType.NUMBER;
            this.formatter          = new DataFormatter();
            this.value              = new StringBuffer();
            rows.clear();
        }
        public String getDataFormat() {
            return mDataFormat;
        }

        public void setDataFormat(String mDataFormat) {
            this.mDataFormat = mDataFormat;
        }
        public String getTimeFormat() {
            return mTimeFormat;
        }

        public void setTimeFormat(String mTimeFormat) {
            this.mTimeFormat = mTimeFormat;
        }
        public String getDataTimeFormat() {
            return mDataTimeFormat;
        }

        public void setmDataTimeFormat(String mDataTimeFormat) {
            this.mDataTimeFormat = mDataTimeFormat;
        }
        @Override
        public void startElement(String uri, String localName, String name, Attributes attributes){
            if ("inlineStr".equals(name) || "v".equals(name)) {
                vIsOpen = true;
                value.setLength(0);
            } else if ("c".equals(name)) {// c => cell
                String           r = attributes.getValue("r");
                int     firstDigit = -1;
                for (int c = 0; c < r.length(); ++c) {
                    if (Character.isDigit(r.charAt(c))) {
                        firstDigit = c;
                        break;
                    }
                }
                this.thisColumn     = nameToColumn(r.substring(0, firstDigit));
                this.nextDataType   = XSSFDataType.NUMBER;
                this.formatIndex    = -1;
                this.formatString   = null;
                String cellType     = attributes.getValue("t");
                String cellStyleStr = attributes.getValue("s");
                if ("b".equals(cellType)) {
                    nextDataType = XSSFDataType.BOOL;
                } else if ("e".equals(cellType)) {
                    nextDataType = XSSFDataType.ERROR;
                } else if ("inlineStr".equals(cellType)) {
                    nextDataType = XSSFDataType.INLINESTR;
                } else if ("s".equals(cellType)) {
                    nextDataType = XSSFDataType.SSTINDEX;
                } else if ("str".equals(cellType)) {
                    nextDataType = XSSFDataType.FORMULA;
                } else if (cellStyleStr != null) {
                    int           styleIndex = Integer.parseInt(cellStyleStr);
                    XSSFCellStyle style      = stylesTable.getStyleAt(styleIndex);
                    this.formatIndex         = style.getDataFormat();
                    this.formatString        = style.getDataFormatString();
                    if (this.formatString == null) {
                        this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
                    }
                }
            }

        }
        @Override
        public void endElement(String uri, String localName, String name) {
            String thisStr = null;
            if ("v".equals(name)) {// v => contents of a cell
                switch (nextDataType) {
                    case BOOL:
                        char first = value.charAt(0);
                        thisStr = first == '0' ? "FALSE" : "TRUE";
                        break;
                    case ERROR:
                        thisStr = "\"ERROR:" + value.toString() + '"';
                        break;
                    case FORMULA:
                        thisStr =value.toString();
                        break;
                    case INLINESTR:
                        // TODO: have seen an example of this, so it's untested.
                        XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                        thisStr = rtsi.toString() ;
                        break;
                    case SSTINDEX:
                        String sstIndex = value.toString();
                        try {
                            int idx = Integer.parseInt(sstIndex);
                            thisStr =sharedStringsTable.getItemAt(idx).toString();
                        } catch (NumberFormatException ex) {
//                        System.out.println("Failed to parse SST index '" + sstIndex + "': " + ex.toString());
                        }
                        break;
                    case NUMBER:
                        String n = value.toString();
                        if (HSSFDateUtil.isADateFormat(this.formatIndex, n)) {
                            Double  d    = Double.parseDouble(n);
                            Date date = HSSFDateUtil.getJavaDate(d);
                            thisStr = formateDateToString(date);
                        } else if (this.formatString != null) {
                            thisStr = formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex,this.formatString);
                        } else {
                            thisStr = n;
                        }
                        break;
                    default:
                        thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
                        break;
                }
                if (lastColumnNumber == -1) {
                    lastColumnNumber = 0;
                }
                //判断单元格的值是否为空
                if (thisStr == null || "".equals(isCellNull)) {
                    isCellNull = true;// 设置单元格是否为空值
                }
                record.add(thisStr);
                if (thisColumn > -1) {
                    lastColumnNumber = thisColumn;
                }

            } else if ("row".equals(name)) {
                if (lastColumnNumber == -1) {
                    lastColumnNumber = 0;
                }
                if (!isCellNull) {
                    rows.add(record.toArray(new String[]{}));
                    isCellNull = false;
                    record.clear();
                }
                lastColumnNumber = -1;
            }
        }

        public List<String[]> getRows() {
            return rows;
        }

        public void setRows(List<String[]> rows) {
            this.rows = rows;
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (vIsOpen) {
                value.append(ch, start, length);
            }
        }

        private int nameToColumn(String name) {
            int column = -1;
            for (int i = 0; i < name.length(); ++i) {
                int c = name.charAt(i);
                column = (column + 1) * 26 + c - 'A';
            }
            return column;
        }

        private String formateDateToString(Date date) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            if(c.get(Calendar.YEAR) < 1900){
                return new SimpleDateFormat(getTimeFormat()).format(date);
            } else {
                if(c.get(Calendar.HOUR_OF_DAY) == 0 && c.get(Calendar.MINUTE)==0 && c.get(Calendar.SECOND)==0) {
                    return new SimpleDateFormat(getDataFormat()).format(date);
                } else {
                    return new SimpleDateFormat(getDataTimeFormat()).format(date);
                }
            }
        }
    }
}
