public class Person {
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public int dx;
    public int dy;
    public boolean isRight;
    public boolean isLeft;
    public boolean isUp;
    public boolean isDown;

    public Person (int pWidth, int pHeight, int pXpos, int pYpos, int pDx, int pDy){
        width = pWidth;
        height = pHeight;
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx;
        dy = pDy;
        isRight = false;
        isLeft = false;
        isUp = false;
        isDown = false;

    }

    public void Shout(){
        System.out.println("HEY!");
    }
}
