package dataclass;
import java.sql.*;
import java.util.*;
public class GoodsFactory {
	private List goodsList=new ArrayList();
	public GoodsFactory(){
		CommonADO ado=CommonADO.getCommonADO();
		String goodsQueryStr="select * from Goods";
		ResultSet rs=ado.executeSelect(goodsQueryStr);
		try {
			while(rs.next()){
				GoodsEntity goods=new GoodsEntity();
				goods.setGoodsNo(rs.getString("GoodsNo"));
				goods.setGoodsName(rs.getString("GoodsName"));
				goods.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List getGoodsList(){
		return goodsList;
	}
}
