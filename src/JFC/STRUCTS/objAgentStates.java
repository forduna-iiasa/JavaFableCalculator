package linkerdist;

public class
objAgentStates {
    public int[] Currents;
    public int[] Last;
    objAgentStates(){
        this.Currents = new int[]{0,0,0,0,0,0,0};
        this.Last = new int[]{0,0,0,0,0,0,0};
    }
    public int GetCurrentYear(int num) { return this.Currents[GetYearLocation(num)]; }
    public void SetCurrentYear(int state, int year) { this.Currents[GetYearLocation(year)]=state; }

    public int GetLastYear(int num) { return this.Last[GetYearLocation(num)]; }
    public void SetLastYear(int state,int year) { this.Last[GetYearLocation(year)]=state; }

    public int GetYearLocation(int y){
        int l = 0;
        switch(y){
            case 2020:
                l = 0;
                break;
            case 2025:
                l = 1;
                break;
            case 2030:
                l = 2;
                break;
            case 2035:
                l = 3;
                break;
            case 2040:
                l = 4;
                break;
            case 2045:
                l = 5;
                break;
            case 2050:
                l = 6;
                break;
        }
        return l;
    }
}
