package com.luckey.socket;

import org.apache.mina.core.session.IoSession;

public class SocketAppPacket {
    // 最新版本检查
    public static final int COMMAND_ID_GET_APP_VERSION = 0x00000001;
    // 用户登录
    public static final int COMMAND_ID_USER_LOGIIN = 0x00001002;
    // 用户注册
    public static final int COMMAND_ID_USER_REGISTER = 0x00001001;
    // 修改密码
    public static final int COMMAND_ID_UPDATE_PASSWORD = 0x00001003;
    // 重置密码
    public static final int COMMAND_ID_RESET_PASSWORD = 0x00001004;

    public static final int COMMAND_ID_UPDATE_PROFILE = 0x00001005;
//下载吸烟数据

    public static final int COMMAND_ID_Upload_Smoking_Data = 0x00002001;
    public static final int COMMAND_ID_DOWNLOAD_Smoking_Data = 0x00002002;
    public static final int COMMAND_ID_Upload_Smoking_Location_Data = 0x00002003;
    public static final int COMMAND_ID_Download_Smoking_Location_Data = 0x00002004;
    public static final int COMMAND_ID_Upload_Heart_Rate_Data = 0x00002005;
    public static final int COMMAND_ID_Download_Heart_Rate_Data = 0x00002006;

    //blog
    public static final int COMMAND_ID_Get_Blog_List = 0x00003003;
    public static final int COMMAND_ID_New_Blog = 0x00003001;
    public static final int COMMAND_ID_New_Comment = 0x00003002;
    public static final int COMMAND_ID_Get_Comment_List = 0x00003004;
    public static final int COMMAND_ID_Delete_Blog = 0x00003005;
    public static final int COMMAND_ID_Delete_Comment = 0x00003006;
    public static final int COMMAND_ID_New_Like = 0x00003007;
    public static final int COMMAND_ID_Get_Like_list = 0x00003008;

    private IoSession fromClient = null;

    /**
     * @return the fromClient
     */
    public IoSession getFromClient() {
        return fromClient;
    }

    public SocketAppPacket(IoSession channel) {
        this.fromClient = channel;
    }

    String packetType;

    public String getPacketType() {
        return packetType;
    }

    /**
     * @param packetType the packetType to set
     */
    private void setPacketType(String packetType) {
        this.packetType = packetType;
    }


    private int commandId = 0;

    /**
     * @return the commandId
     */
    public int getCommandId() {
        return commandId;
    }

    /**
     * @param commandId the commandId to set
     */
    public void setCommandId(int commandId) {
        this.commandId = commandId;

//		String typeString = "0x" + Integer.toHexString(commandId).toUpperCase() + "_";
//		switch (commandId) {
//		// 最新版本检查
//		case SocketAppPacket.COMMAND_ID_GET_APP_VERSION:
//			typeString += "GET_APP_VERSION";
//			break;
//		// 用户登录
//		case SocketAppPacket.COMMAND_ID_USER_LOGIIN:
//			typeString += "USER_LOGIN";
//			break;
//		default:
//			typeString += "UNKNOWN";
//			break;
//		}
//
//		setPacketType(typeString);
    }

    private byte[] commandData = null;

    /**
     * @return the commandData
     */
    public byte[] getCommandData() {
        return commandData;
    }

    /**
     * @param commandData the commandData to set
     */
    public void setCommandData(byte[] commandData) {
        this.commandData = commandData;
    }

    long receiveTime = 0;

    /**
     * @return the receiveTime
     */
    public long getReceiveTime() {
        return receiveTime;
    }

    /**
     * @param receiveTime the receiveTime to set
     */
    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }

}
