package org.conan.websocket;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;

/**
 * Created by xiaoyun on 2016/8/8.
 */
public class DemoServlet extends WebSocketServlet {

	private static ArrayList<MyMessageInbound> mmiList = new ArrayList<MyMessageInbound>();

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol) {
		return new MyMessageInbound();
	}

	private class MyMessageInbound extends MessageInbound {
		WsOutbound myoutbound;

		@Override
		public void onOpen(WsOutbound outbound) {
			try {
				System.out.println("Open Client.");
				this.myoutbound = outbound;
				mmiList.add(this);
				outbound.writeTextMessage(CharBuffer.wrap("Hello!"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onClose(int status) {
			System.out.println("Close Client.");
			mmiList.remove(this);
		}

		@Override
		protected void onBinaryMessage(ByteBuffer message) throws IOException {

		}

		@Override
		public void onTextMessage(CharBuffer cb) throws IOException {
			System.out.println("Accept Message : " + cb);
			for (MyMessageInbound mmib : mmiList) {
				CharBuffer buffer = CharBuffer.wrap(cb);
				mmib.myoutbound.writeTextMessage(buffer);
				mmib.myoutbound.flush();
			}
		}

		public int getReadTimeout() {
			return 0;
		}
	}
}
