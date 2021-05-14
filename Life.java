public class Life {

    public static void main(String[] args) throws InterruptedException {

        // create a life board
        Draw_Board draw = new Draw_Board();
        
        // do this until quit
        while (true) {
            // sleep for a ms to allow variables to set up
            Thread.sleep(1);
            // if we are all set to animate
            if (Draw_Board.animate == true) {
                // update the game board
                Update_GameBoard.Next_Generation();
                // and paint
                Draw_Board.component.paintImmediately(0, 0, 970, 970);
                // now delay an amount of time based on how fast the user wants to go
                try {
                    Thread.sleep(Draw_Board.delay);
                } catch (InterruptedException e1) {
                }
            }
        }
    }
}