//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

import com.yinhe.ec.cpps.model.Cmd;
import com.yinhe.ec.cpps.model.NbMeterPwd;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class YinheWaterFrame188 {
    private static final Logger logger = Logger.getLogger(YinheFrame.class);

    public YinheWaterFrame188() {
    }

    public static String encodeFrame(String meterNo, String commandCode, String commandValue, Cmd cmd, NbMeterPwd nbMeterPwd) {
        logger.info("====188组帧====meterNo:" + meterNo + " commandCode:" + commandCode + " commandValue:" + commandValue);
        String header = "68";
        String type = "10";
        String meterAddr = DataConvertTools.turnOver(DataConvertTools.formatHexStr(meterNo, 7), false, false);
        String control = cmd.getCmdCode();
        String dataLen = cmd.getDataLength();
        String dataItem = cmd.getDataItem();
        String ser = "00";
        String dataStr = "";
        String csStr = "";
        String endStr = "16";
        if ("810A".equals(dataItem)) {
            meterAddr = "AAAAAAAAAAAAAA";
        }

        if ("A014".equals(dataItem)) {
            dataStr = "01";
        }

        String d;
        String tmpCommandValue;
        if (cmd.getCmdId() > 40 && cmd.getCmdId() < 50) {
            switch (cmd.getCmdId()) {
                case 41:
                    d = commandValue.split("@")[1];
                    tmpCommandValue = commandValue.split("@")[0];
                    String d1 = tmpCommandValue.split("\\|")[0].split(":")[0];
                    String d2 = tmpCommandValue.split("\\|")[0].split(":")[1];
                    String p1 = tmpCommandValue.split("\\|")[1].split(":")[0];
                    String p2 = tmpCommandValue.split("\\|")[1].split(":")[1];
                    String p3 = tmpCommandValue.split("\\|")[1].split(":")[2];
                    if ("".equals(d)) {
                        d = "1";
                    }

                    if ("".equals(d1)) {
                        d1 = "0";
                        d2 = "0";
                    }

                    if ("".equals(d2)) {
                        d2 = "0";
                    }

                    if ("".equals(p1)) {
                        p1 = "0";
                        p2 = "0";
                        p3 = "0";
                    }

                    if ("".equals(p2)) {
                        p2 = "0";
                        p3 = "0";
                    }

                    if ("".equals(p3)) {
                        p3 = "0";
                    }

                    d = DataConvertTools.formatHexStr(d, 1);
                    d1 = DataConvertTools.formatHexStr(d1, 3);
                    d2 = DataConvertTools.formatHexStr(d2, 3);
                    p1 = DataConvertTools.formatHexStr(String.valueOf((new Double(Double.valueOf(p1) * 100.0)).intValue()), 3);
                    p2 = DataConvertTools.formatHexStr(String.valueOf((new Double(Double.valueOf(p2) * 100.0)).intValue()), 3);
                    p3 = DataConvertTools.formatHexStr(String.valueOf((new Double(Double.valueOf(p3) * 100.0)).intValue()), 3);
                    commandValue = DataConvertTools.turnOver(p1, false, false) + DataConvertTools.turnOver(d1, false, false) + DataConvertTools.turnOver(p2, false, false) + DataConvertTools.turnOver(d2, false, false) + DataConvertTools.turnOver(p3, false, false) + d;
                    break;
                case 42:
                    commandValue = DataConvertTools.formatHexStr(commandValue, 1);
                    break;
                case 43:
                    String count = commandValue.split(":")[0];
                    String money = commandValue.split(":")[1];
                    if ("".equals(count)) {
                        count = "1";
                    }

                    if ("".equals(money)) {
                        money = "0";
                    }

                    count = DataConvertTools.Integer2HexStr(Integer.parseInt(count));
                    money = DataConvertTools.formatHexStr(String.valueOf((new Double(Double.valueOf(money) * 100.0)).intValue()), 4);
                    commandValue = count + DataConvertTools.turnOver(money, false, false);
                    break;
                case 44:
                    String time = Tools.getNow_YYYYMMDDHHMMSS();
                    commandValue = DataConvertTools.turnOver(time, false, false);
                    break;
                case 45:
                    commandValue = DataConvertTools.formatHexStr(commandValue, 1);
                case 46:
                default:
                    break;
                case 47:
                    String gj = commandValue.split(":")[0];
                    String xg = commandValue.split(":")[1];
                    if ("".equals(gj)) {
                        gj = "0";
                    }

                    if ("".equals(xg)) {
                        xg = "0";
                    }

                    gj = DataConvertTools.formatHexStr(gj, 1);
                    xg = DataConvertTools.formatHexStr(xg, 2);
                    commandValue = DataConvertTools.turnOver(gj + xg, false, false);
                    break;
                case 48:
                    commandValue = DataConvertTools.turnOver(DataConvertTools.formatHexStr(meterNo, 7), false, false);
            }

            dataStr = commandValue;
        }

        d = header + type + meterAddr + control + dataLen + dataItem + ser + dataStr;
        tmpCommandValue = "";
        csStr = DataConvertTools.cs(d);
        tmpCommandValue = d + csStr + endStr;
        logger.info("====组帧结果====" + DataConvertTools.formatPrint(tmpCommandValue));
        return tmpCommandValue;
    }

    public static Map<String, String> decodeFrame(String reciveFrame) {
        reciveFrame = reciveFrame.toUpperCase();
        logger.info("====188解帧====" + DataConvertTools.formatPrint(reciveFrame));
        String meterNo = "";
        String commandCode = "";
        String value = "";
        String reciveDesc = "";
        String state = "";
        String readTime = "";
        String dosage1 = "";
        String dosage2 = "";
        Frame frame = new Frame();
        if ("".equals(reciveFrame)) {
            return null;
        } else {
            byte[] buffer = DataConvertTools.hexString2Bytes(reciveFrame.replace("FE", "").replace("fe", ""));

            int i;
            for(i = 0; i < 7; ++i) {
                frame.address[i] = buffer[8 - i];
            }

            frame.c = buffer[9];
            frame.error = true;
            frame.dataLength = buffer[10];

            for(i = 0; i < 2; ++i) {
                frame.dataItem[i] = buffer[11 + i];
            }

            commandCode = DataConvertTools.bytesToHexString(frame.dataItem);
            if ((255 & buffer[9]) == 131) {
                frame.data = new byte[7];
            } else {
                frame.data = new byte[frame.dataLength - 3];
            }

            if (frame.dataLength > 0 && frame.error) {
                if ((255 & buffer[9]) == 131 && "810A".equals(commandCode)) {
                    for(i = 0; i < 7; ++i) {
                        frame.data[i] = buffer[8 - i];
                    }

                    value = DataConvertTools.bytesToHexString(frame.data);
                    reciveDesc = value;
                    logger.info("读表号:" + value);
                } else {
                    String st;
                    if ((255 & buffer[9]) == 129 && "901F".equals(commandCode)) {
                        for(i = 0; i < frame.dataLength - 3; ++i) {
                            frame.data[i] = buffer[10 + frame.dataLength - i];
                        }

                        st = DataConvertTools.bytesToHexString(frame.data);
                        logger.info("上报当前量数据帧:" + st);
                        if (st.length() >= 38) {
                            state = st.substring(2, 4);
                            state = DataConvertTools.hexToBinary(state);
                            logger.info("上报当前量数据帧state:" + state);
                            state = state.substring(6, 8);
                            if ("00".equals(state)) {
                                state = "0";
                            } else if ("01".equals(state)) {
                                state = "1";
                            } else {
                                state = "2";
                            }

                            readTime = st.substring(4, 18);
                            dosage1 = st.substring(20, 28);
                            dosage1 = Tools.formatFloat(Double.parseDouble(dosage1) * 0.01);
                            dosage2 = st.substring(30, 38);
                            dosage2 = Tools.formatFloat(Double.parseDouble(dosage2) * 0.01);
                            value = dosage2;
                            reciveDesc = "当前累计量:" + dosage2 + "，结算日累计量:" + dosage1 + "，实时时间:" + readTime + "，状态:" + state;
                        }

                        logger.info("state:" + state + " readTime:" + readTime + " dosage1:" + dosage1 + " dosage2:" + dosage2);
                    } else if ((255 & buffer[9]) == 129 && commandCode.contains("D12")) {
                        for(i = 0; i < frame.dataLength - 3; ++i) {
                            frame.data[i] = buffer[10 + frame.dataLength - i];
                        }

                        st = DataConvertTools.bytesToHexString(frame.data);
                        value = st.substring(2, 10);
                        value = Tools.formatFloat(Double.parseDouble(value) * 0.01);
                        reciveDesc = value;
                        logger.info("上报月冻结数据帧:" + st);
                        logger.info("上报月冻结数据帧value:" + value);
                    } else if ((255 & buffer[9]) == 132 && "A017".equals(commandCode)) {
                        for(i = 0; i < frame.dataLength - 3; ++i) {
                            frame.data[i] = buffer[10 + frame.dataLength - i];
                        }

                        st = DataConvertTools.bytesToHexString(frame.data);
                        state = st.substring(2, 4);
                        state = DataConvertTools.hexToBinary(state);
                        state = state.substring(6, 8);
                        if ("00".equals(state)) {
                            state = "0";
                            reciveDesc = "开通";
                        } else if ("01".equals(state)) {
                            state = "1";
                            reciveDesc = "关断";
                        } else {
                            state = "2";
                            reciveDesc = "异常";
                        }

                        value = state;
                        logger.info("上报阀门控制数据帧:" + st);
                        logger.info("上报阀门控制数据帧value:" + state);
                    } else {
                        for(i = 0; i < frame.dataLength - 3; ++i) {
                            frame.data[i] = buffer[10 + frame.dataLength - i];
                        }

                        value = DataConvertTools.bytesToHexString(frame.data);
                        reciveDesc = value;
                        String left;
                        String total;
                        String thisbuy;
                        String count;
                        if ("8102".equals(commandCode)) {
                            st = value.substring(24, 30);
                            st = Tools.formatFloat(Double.parseDouble(st) * 0.01);
                            left = value.substring(18, 24);
                            left = "" + Integer.valueOf(left);
                            total = value.substring(12, 18);
                            total = Tools.formatFloat(Double.parseDouble(total) * 0.01);
                            thisbuy = value.substring(6, 12);
                            thisbuy = "" + Integer.valueOf(thisbuy);
                            count = value.substring(0, 6);
                            count = Tools.formatFloat(Double.parseDouble(count) * 0.01);
                            value = left + ":" + thisbuy + "|" + st + ":" + total + ":" + count;
                            reciveDesc = "价格一：" + st + "，用量一：" + left + "，价格二：" + total + "，用量二：" + thisbuy + "，价格三：" + count;
                            logger.info("上报阶梯价格数据帧:" + reciveDesc);
                        } else if ("8105".equals(commandCode)) {
                            st = value.substring(2, 4);
                            st = DataConvertTools.hexToBinary(st);
                            st = st.substring(6, 8);
                            left = value.substring(4, 12);
                            left = Tools.formatFloat(Double.parseDouble(left) * 0.01);
                            total = value.substring(12, 20);
                            total = Tools.formatFloat(Double.parseDouble(total) * 0.01);
                            thisbuy = value.substring(20, 28);
                            thisbuy = Tools.formatFloat(Double.parseDouble(thisbuy) * 0.01);
                            count = String.valueOf(Integer.parseInt(value.substring(28, 30), 16));
                            value = count + ":" + thisbuy + ":" + total + ":" + left + ":" + st;
                            reciveDesc = "购买次数：" + count + "，本次购买：" + thisbuy + "，累计购买：" + total + "，剩余金额：" + left + "，状态：" + st;
                            logger.info("上报购买数据帧:" + reciveDesc);
                        } else if ("8110".equals(commandCode)) {
                            reciveDesc = "告警值：" + value.substring(0, 2) + "，限购值：" + value.substring(2, 6);
                        } else if ("A013".equals(commandCode)) {
                            reciveDesc = "本次购买序号：" + value.substring(8, 10) + "，本次购入金额：" + Tools.formatFloat(Double.parseDouble(value.substring(0, 8)) * 0.01);
                        }
                    }
                }
            }

            meterNo = DataConvertTools.bytesToHexString(frame.address);
            Map<String, String> map = new HashMap();
            map.put("meterNo", meterNo);
            map.put("commandCode", commandCode);
            map.put("value", value);
            map.put("reciveDesc", reciveDesc);
            map.put("state", state);
            map.put("readTime", readTime);
            map.put("dosage1", dosage1);
            map.put("dosage2", dosage2);
            map.put("valid", String.valueOf(frame.error));
            logger.info(map);
            return map;
        }
    }

    public static void main(String[] args) {
        try {
            decodeFrame("68107267100720000081128105000010100000101000000010000011FF0116");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    static class Frame {
        public boolean error = false;
        public byte[] address = new byte[7];
        public byte c;
        public byte[] dataItem = new byte[2];
        public byte[] data;
        public byte dataLength;

        public Frame() {
        }
    }
}
