package pdl.model;

public class videoInfo {
	private String  id;
	private String  videoName;
	private String  videoSize;
	private String  type;
	
	public videoInfo(){
		super();
	}
	
	public videoInfo(String id,String videoName,String videoSize,String type){
		this.id=id;
		this.videoName=videoName;
		this.videoSize=videoSize;
		this.type=type;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoSize() {
		return videoSize;
	}
	public void setVideoSize(String videoSize) {
		this.videoSize = videoSize;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
