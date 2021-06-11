package Model.Gestione;

public class Paginatore {

    private final int limit;
    private final int offset;

    public Paginatore(int page,int itemsPerPage){
        this.limit = itemsPerPage;
        this.offset = (page -1) * itemsPerPage + 1;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
