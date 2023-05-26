package com.xuyh.utils;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JsonUtilTest {
    @Test
    public void test() throws Exception {
        String jsonString = "{\"_id\":\"623f92b691505bd6e44b41e9\",\"session_id\":\"3414892428501450755\",\"global_serial_num\":\";1;2;4;2;3;3;4;\",\"acw_duration\":1,\"alert_duration\":8,\"alert_duration_agent\":6,\"queue_duration\":0,\"talk_duration\":40,\"ivr_duration\":0,\"ivr_time\":0,\"transfer_agent_time\":0,\"dial_times\":0,\"transfer_queue_time\":0,\"transfer_altering_time\":0,\"alter_time\":0,\"isUpdateNum\":2,\"insert_time\":\"1648333865000\",\"start_time\":\"1679987236000\",\"remote_url\":\"TEL:13236033083\",\"end_time\":\"1679987265000\",\"agent_id\":\"03260026\",\"agent_name\":\"03260026\",\"agent_dn\":\"SIP:1335\",\"local_url\":\"SIP:1335\",\"region_id\":\"010\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"end_reason\":\"0\",\"dest_ani\":\"20200910\",\"session_detail\":[{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987236:1679987239:204:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":3,\"end_reason\":\"0\",\"end_time\":\"1679987239\",\"end_time_halfhour\":\"1679986800\",\"end_type\":2,\"ent_id\":\"20200910\",\"global_serial_num\":2,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":1,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987236\",\"start_type\":2,\"state_num\":1,\"status\":204,\"sub_serviceid\":\"1\",\"user_data\":\"\",\"session_info\":{\"_id\":\"623f92b691505bd6e44b41e9\",\"session_id\":\"3414892428501450755\",\"global_serial_num\":\";1;2;4;2;3;3;4;\",\"acw_duration\":1,\"alert_duration\":8,\"alert_duration_agent\":6,\"queue_duration\":0,\"talk_duration\":40,\"ivr_duration\":0,\"ivr_time\":0,\"transfer_agent_time\":0,\"dial_times\":0,\"transfer_queue_time\":0,\"transfer_altering_time\":0,\"alter_time\":0,\"isUpdateNum\":2,\"insert_time\":\"1648333865000\",\"start_time\":\"1679987236000\",\"remote_url\":\"TEL:13236033083\",\"end_time\":\"1679987265000\",\"agent_id\":\"03260026\",\"agent_name\":\"03260026\",\"agent_dn\":\"SIP:1335\",\"local_url\":\"SIP:1335\",\"region_id\":\"010\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"end_reason\":\"0\",\"dest_ani\":\"20200910\",\"session_detail\":[{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987236:1679987239:204:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026000\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":3,\"end_reason\":\"0\",\"end_time\":\"1679987239\",\"end_time_halfhour\":\"1679986800\",\"end_type\":2,\"ent_id\":\"20200910\",\"global_serial_num\":2,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":1,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987236\",\"start_type\":2,\"state_num\":1,\"status\":204,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987245:1679987265:205:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026000\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":20,\"end_reason\":\"0\",\"end_time\":\"1679987265\",\"end_time_halfhour\":\"1679986800\",\"end_type\":255,\"ent_id\":\"20200910\",\"global_serial_num\":4,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":3,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987245\",\"start_type\":2,\"state_num\":1,\"status\":205,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987236:1679987239:204:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026000\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":3,\"end_reason\":\"0\",\"end_time\":\"1679987239\",\"end_time_halfhour\":\"1679986800\",\"end_type\":2,\"ent_id\":\"20200910\",\"global_serial_num\":2,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":1,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987236\",\"start_type\":2,\"state_num\":1,\"status\":204,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987241:1679987245:204:2\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026000\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":4,\"end_reason\":\"0\",\"end_time\":\"1679987245\",\"end_time_halfhour\":\"1679986800\",\"end_type\":107,\"ent_id\":\"20200910\",\"global_serial_num\":3,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":2,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987241\",\"start_type\":105,\"state_num\":2,\"status\":204,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987241:1679987245:204:2\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026000\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":4,\"end_reason\":\"0\",\"end_time\":\"1679987245\",\"end_time_halfhour\":\"1679986800\",\"end_type\":107,\"ent_id\":\"20200910\",\"global_serial_num\":3,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":2,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987241\",\"start_type\":105,\"state_num\":2,\"status\":204,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987245:1679987265:205:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026000\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":20,\"end_reason\":\"0\",\"end_time\":\"1679987265\",\"end_time_halfhour\":\"1679986800\",\"end_type\":255,\"ent_id\":\"20200910\",\"global_serial_num\":4,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":3,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987245\",\"start_type\":2,\"state_num\":1,\"status\":205,\"sub_serviceid\":\"1\",\"user_data\":\"\"}],\"call_type\":1,\"end_type\":\"255\"}},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987245:1679987265:205:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":20,\"end_reason\":\"0\",\"end_time\":\"1679987265\",\"end_time_halfhour\":\"1679986800\",\"end_type\":255,\"ent_id\":\"20200910\",\"global_serial_num\":4,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":3,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987245\",\"start_type\":2,\"state_num\":1,\"status\":205,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987236:1679987239:204:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":3,\"end_reason\":\"0\",\"end_time\":\"1679987239\",\"end_time_halfhour\":\"1679986800\",\"end_type\":2,\"ent_id\":\"20200910\",\"global_serial_num\":2,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":1,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987236\",\"start_type\":2,\"state_num\":1,\"status\":204,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987241:1679987245:204:2\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":4,\"end_reason\":\"0\",\"end_time\":\"1679987245\",\"end_time_halfhour\":\"1679986800\",\"end_type\":107,\"ent_id\":\"20200910\",\"global_serial_num\":3,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":2,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987241\",\"start_type\":105,\"state_num\":2,\"status\":204,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987241:1679987245:204:2\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":4,\"end_reason\":\"0\",\"end_time\":\"1679987245\",\"end_time_halfhour\":\"1679986800\",\"end_type\":107,\"ent_id\":\"20200910\",\"global_serial_num\":3,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":2,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987241\",\"start_type\":105,\"state_num\":2,\"status\":204,\"sub_serviceid\":\"1\",\"user_data\":\"\"},{\"_id\":\"DDCS-CLOUD47:20200910:03260026:SIP:1335:TEL:13236033083:1679987245:1679987265:205:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:1335\",\"agent_id\":\"03260026\",\"agent_name\":\"03260026\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"20200910\",\"duration\":20,\"end_reason\":\"0\",\"end_time\":\"1679987265\",\"end_time_halfhour\":\"1679986800\",\"end_type\":255,\"ent_id\":\"20200910\",\"global_serial_num\":4,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":1,\"local_url\":\"SIP:1335\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"MERGED\",\"region_id\":\"010\",\"remote_url\":\"TEL:13236033083\",\"remote_url_wx\":\"\",\"serial_num\":3,\"session_id\":\"3414892428501450755\",\"skill_id\":\"54\",\"skill_name\":\"test99999\",\"skill_type\":\"\",\"start_time\":\"1679987245\",\"start_type\":2,\"state_num\":1,\"status\":205,\"sub_serviceid\":\"1\",\"user_data\":\"\"}],\"call_type\":1,\"end_type\":\"255\"}";
        String jsonKey = "ROOT.session_detail._id";
        String result = JsonUtil.getJsonValue(jsonString, jsonKey);
        System.out.println(result);
    }
}
