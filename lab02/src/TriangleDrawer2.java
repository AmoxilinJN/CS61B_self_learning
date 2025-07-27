public class TriangleDrawer2 {
    public static void drawTriangle(){
        int size=5,row=1,col=0;
        for(;col<row;col++){
            if(col==row-1){
                System.out.println("*");
                row++;
                col=0;
                if(row==size+1){break;}
                continue;
            }
            System.out.print("*");
        }
    }
    public static void main(String[] args){
        drawTriangle();
    }

}
