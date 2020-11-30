package Game;

/** Klasa słuzaca do sledzenia poziomu rozgrywki*/
class GameStatus{
    /** Poziom rozgrywki */
    public int level;
    /** Zienianie poziomu na wyższy */
    public void nextLevel(){
        level++;
    }
}