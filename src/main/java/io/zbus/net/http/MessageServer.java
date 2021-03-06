package io.zbus.net.http;

import java.util.List;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.zbus.net.CodecInitializer;
import io.zbus.net.IoDriver;
import io.zbus.net.tcp.TcpServer;

public class MessageServer extends TcpServer { 
	public MessageServer() {
		this(null);
	}

	public MessageServer(final IoDriver driver) {
		super(driver);  
		codec(new CodecInitializer() {
			@Override
			public void initPipeline(List<ChannelHandler> p) {
				p.add(new HttpServerCodec());
				p.add(new HttpObjectAggregator(getIoDriver().getPackageSizeLimit()));
				p.add(new MessageToHttpWsCodec());
			}
		}); 
	} 
}
