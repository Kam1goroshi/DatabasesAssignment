package db_agent;
abstract class PhysicalEntity{
    /**
     * @return string that a physical entity uses for an sql insertion
     */
    abstract String getInsertStatement();
}