package db_agent;
abstract class PhysicalEntity{
    /**
     * @return string that a physical entity uses for an sql insertion
     */
    abstract String getInsertStatement();

    /**
     * @return string that a physical entity uses for an sql query based on primary key
     */
    abstract String getPrimaryKeyQuery();
}