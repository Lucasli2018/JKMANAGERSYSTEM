import cn.itcast.util.Arith;
/**
 * @Description:  浮点数精度处理
 * @Author: nutony
 * @Date: 2013-05-15
 */
public class TestArith {
	public static void main(String[] args) {

		double a = (3.3-2.4)/0.1;
		System.out.println(a);
		
		
		Arith arith = new Arith();
		
		double d1 = 3.3-2.4;
		double d2 = 0.1;
		System.out.println(arith.div(d1, d2));
	}

}
