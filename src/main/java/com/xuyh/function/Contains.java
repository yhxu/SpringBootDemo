package com.xuyh.function;

import com.xuyh.annotation.Function;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * @Author: XUYH
 * @Description: 包含函数
 * @Date: 2019/3/4
 * @Version:
 */
@Function("Contains")
public class Contains extends PostfixMathCommand {
    public Contains() {
        //参数数量
        numberOfParameters = 2;
    }
    public void run(Stack inStack) throws ParseException {

        checkStack(inStack);
        //此处参数弹出顺序和参数列表顺序相反，即最后一个参数先弹出
        String keyWord = (String)inStack.pop();
        String summary = (String)inStack.pop();

        double res = 0.0;

        if(summary.contains(keyWord)) {
            res = 1.0;
        }
        //对象作为返回值
        inStack.push(res);
    }
}
