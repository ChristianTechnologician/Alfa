package Model.Search;

public enum Operator
{
    GT, LT, EQ, NE, GE, LE, MATCH,RANGE;

    public String toString()
    {
        return switch(this)
                {
                    case LT -> " < ";
                    case EQ -> " = ";
                    case GE -> " >= ";
                    case NE -> " != ";
                    case GT -> " > ";
                    case LE -> " <= ";
                    case MATCH -> " LIKE ";
                    case RANGE -> ">=,<=";
                };
    }
}
