/**
 * This class generates and outputs random points yielding <code> x<code> values within the following range:[-Panel2D.WIDTH/2, Panel2D.WIDTH/2)
 * and <code> y <code> values within the following range: [-Panel2D.HEIGHT/2, Panel2D.HEIGHT/2)
 * @author Dara Adekore
 *
 */
public class Generate {

	public static void main(String[] args) {
		int x ;
		int y ;
		for(int i = 0; i<=11; i++) { 
			 x = (Random.rand(Panel2D.WIDTH/2)+ (-Random.rand(Panel2D.WIDTH/2)));
			 y = (Random.rand(Panel2D.WIDTH/2)+ (-Random.rand(Panel2D.HEIGHT/2)));;
			System.out.println((x+" "+y));
		}
	}

}
