package dataclass;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

public class GoodsShow extends CLabel {
	private GoodsEntity good=null;
	public GoodsShow(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}
	public GoodsEntity getGood(){
		return good;
	}
	public void setGood(GoodsEntity good){
		this.good=good;
	}
}
