/**
 * This class collects the points generated and proceeds to sort and label them in increasing proximity
 * to the point of origin.
 * The points are then displayed along with a circle to help isolate half of the labeled points
 * ]
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SortCoords {
	
	static Scanner myScan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<Point2D> points = new ArrayList<>();
		int i = 0;
		Point2D centre = new Point2D(0, 0);
		Panel2D aParentpanel = new Panel2D();
		
		System.out.println("Please enter the name of the folder with your Coordinates!");
		String myDir = myScan.nextLine(); // We input our directory into a string
		Path dir = Paths.get(myDir); // we set our path object equal to the directory that we entered from our string
		BufferedReader in;
		
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){//setup directory of paths
			
			for(Path myFile : stream) {//iterate through our paths
				
				in = Files.newBufferedReader(myFile);//inputobject attached to a file
				String myLine;
				String[] myXy;
				
				while((myLine = in.readLine()) != null) {//while there is still content to read in
				
					myXy = myLine.split(" ");
					points.add(
						new Point2D(Integer.parseInt(myXy[0]),
							        Integer.parseInt(myXy[1]))			
					);
				}
				in.close();
			}
		stream.close();	
		}
		points.sort(new Comparator<Point2D>(){//proximity comparison
			
			public int compare(Point2D ptA, Point2D ptB) {
				if(ptA.dist2Origin() < ptB.dist2Origin())
					return -1;
				if(ptA.dist2Origin() == ptB.dist2Origin()) 
					return 0;
				else 
					return 1;
			}
		});
		aParentpanel.drawAxis();
		aParentpanel.circle(centre, 14);
		aParentpanel.pointLabel(points);
		aParentpanel.display();
	}

}
