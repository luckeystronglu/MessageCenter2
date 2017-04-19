
package com.luckey.socket;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import de.greenrobot.event.EventBus;

/**
 * 采用MINA的通信客户端
 * @author Tom Xu
 * @version Revision 1.0
 */

public class SocketClientHandlerListener extends IoHandlerAdapter {

	public SocketClientHandlerListener()
	{
		super();
//		
	}
	
	
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
//        EventBus.getDefault().register(SocketClientHandlerListener.this);
//        byte[] response = (byte[]) message;
//        byte[] msgLen = new byte[4];
//        System.arraycopy(response, 0, msgLen, 0, 4);
//        int commandId = getCommandId(response);
//        byte[] result = new byte[2];
//        System.arraycopy(response, 8, result, 0, 2);
//        switch (commandId) {
//        case 1:
//            System.out.println("msgLen:" + Converser.byte2int(msgLen) + ",commandId:" + commandId
//                    + ",msgBody:" + new String(result));
//            break;
//        case 3:
//            System.out.println("msgLen:" + Converser.byte2int(msgLen) + ",commandId:" + commandId
//                    + ",msgBody:" + new String(result));
//            break;
//        default:
//            break;
//        }
        
        
        SocketAppPacket packet = (SocketAppPacket) message;
        EventBus.getDefault().post(packet);
//		switch (packet.getCommandId()){
//			case SocketAppPacket.COMMAND_ID_USER_LOGIIN:
////				LoginResponse.LoginResponseMessage loginResponseMessage = LoginResponse.LoginResponseMessage.parseFrom(packet.getCommandData());
////				LogUtils.logMessage("user", loginResponseMessage.getErrorMsg().toString());
//				
//				SocketMsg msg=new SocketMsg(SocketAppPacket.COMMAND_ID_USER_LOGIIN,packet.getCommandData());
//				EventBus.getDefault().post(msg);
//				break;
//			default:
//				break;
//		}
//		session.write(packet);
        
        
        
    }

//    private int getCommandId(byte[] req) {
//        byte[] commandId = new byte[4];
//        System.arraycopy(req, 4, commandId, 0, 4);
//        return Converser.byte2int(commandId);
//    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
//    	EventBus.getDefault().unregister(this);
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // TODO Auto-generated method stub
        super.sessionIdle(session, status);
    }

}