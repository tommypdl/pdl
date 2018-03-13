package dataclass;

public class ConsumeEntity {
	private int goodsOrderNo=0;
	private String goodsName=null;
	private String roomNo=null;
	private int consumeNum=0;
	private float goodsTotalPrice=0;
	private String payState=null;
	public int getGoodsOrderNo() {
		return goodsOrderNo;
	}
	public void setGoodsOrderNo(int goodsOrderNo) {
		this.goodsOrderNo = goodsOrderNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public int getConsumeNum() {
		return consumeNum;
	}
	public void setConsumeNum(int consumeNum) {
		this.consumeNum = consumeNum;
	}
	public float getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(float goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public String getPayState() {
		return payState;
	}
	public void setPayState(String payState) {
		this.payState = payState;
	}
	
	
}
