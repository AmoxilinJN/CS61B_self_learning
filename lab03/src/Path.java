/** A class that represents a path via pursuit curves. */
public class Path {
    double x,y;
    Point curr,next;
    // TODO
    public Path(double x,double y){
        this.curr=new Point();
        this.next=new Point(x,y);
    }
    public double getCurrX(){
        return this.curr.getX();
    }
    public double getCurrY(){
        return this.curr.getY();
    }
    public double getNextX(){
        return this.next.getX();
    }
    public double getNextY(){
        return this.next.getY();
    }
    public Point getCurrentPoint(){
        return this.curr;
    }
    public void setCurrentPoint(Point point){
        double x=point.getX();
        double y=point.getY();
        this.curr.setX(x);
        this.curr.setY(y);
    }
    public void iterate(double dx,double dy){
        this.setCurrentPoint(this.next);
        this.next.setX(this.next.getX()+dx);
        this.next.setY(this.next.getY()+dy);
    }
    /*
    public static void main(String[] args){
        Path path = new Path(0, 0);
        path.iterate(1,1);
        System.out.println(path.getNextX());
        System.out.println(path.getNextY());
    }

     */
}
