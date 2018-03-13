package dataclass;

public class GoodsEntity {
	private String GoodsNo=null;
	private String GoodsName=null;
	private float GoodsPrice=0;
	public String getGoodsNo() {
		return GoodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		GoodsNo = goodsNo;
	}
	public String getGoodsName() {
		return GoodsName;
	}
	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}
	public float getGoodsPrice() {
		return GoodsPrice;
	}
	public void setGoodsPrice(float goodsPrice) {
		GoodsPrice = goodsPrice;
	}
	
}
