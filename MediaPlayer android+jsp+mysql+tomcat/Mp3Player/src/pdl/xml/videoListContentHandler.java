package pdl.xml;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import pdl.model.Mp3Info;
import pdl.model.videoInfo;

public class videoListContentHandler extends DefaultHandler{
	private List<videoInfo> infos = null;
	private videoInfo vInfo = null;
	private String tagName = null;
	
	public videoListContentHandler(List<videoInfo> infos){
		super();
		this.infos=infos;
	}
	public List<videoInfo> getInfos() {
		return infos;
	}
	public void setInfos(List<videoInfo> infos) {
		this.infos = infos;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
		String temp = new String(ch, start, length);
		if (tagName.equals("id")) {
		vInfo.setId(temp);
		} else if (tagName.equals("video.name")) {
		vInfo.setVideoName(temp);
		} else if (tagName.equals("video.size")) {
		vInfo.setVideoSize(temp);
		} else if (tagName.equals("video.type")) {
		vInfo.setType(temp);
		} 
	}
	
	@Override
	public void endDocument() throws SAXException {
	}
	@Override
	public void endElement(String uri, String localName, String qName)
	throws SAXException {

	 
	 
	if (qName.equals("resource")) {
	infos.add(vInfo);
	}
	tagName = "";
	}
	@Override
	public void startDocument() throws SAXException {
	// TODO Auto-generated method stub
	super.startDocument();
	}
	@Override
	public void startElement(String uri, String localName, String qName,
	Attributes attributes) throws SAXException {
	this.tagName = localName;
	if (tagName.equals("resource")) {
	vInfo=new videoInfo();
	}
	}
}
