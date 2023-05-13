// Selepe Sello => TebogoYungMercykay
@SuppressWarnings("unchecked")
public class Database {
    String[][] database;
    String[] columnNames;
    Treap<Cell>[] indexes;

    public Database(String[] cols, int maxSize) {
        int x=0;
        this.columnNames=new String[cols.length];
        while (x<this.columnNames.length) {
            this.columnNames[x]=cols[x];
            x+=1;
        }
        int k=0;
        this.database=new String[maxSize][this.columnNames.length];
        while (k<this.database.length) {
            x=0;
            while (x<this.columnNames.length) {
                this.database[k][x]=null;
                x+=1;
            }
            k+=1;
        }
        x=0;
        this.indexes=new Treap[this.columnNames.length];
        while (x<this.columnNames.length) {
            this.indexes[x]=null;
            x+=1;
        }
    }

    public void insert(String[] newRowDetails) throws DatabaseException {
        // TODO: Implementation
        if (newRowDetails.length!=this.columnNames.length) {
            throw DatabaseException.invalidNumberOfColums();
        } else {
            int k=0;
            while (k<this.columnNames.length) {
                if (this.indexes[k]!=null) {
                    String tepString=newRowDetails[k];
                    if (this.indexes[k].access(new Cell(1,tepString))!=null) {
                        throw DatabaseException.duplicateInsert(tepString);
                    }
                }
                k+=1;
            }
            k=0;
            while (k<this.database.length) {
                if (this.database[k][0]==null) {
                    break;
                }
                k+=1;
            }
            if (k>=0 && k<this.database.length) {
                try {
                    int x=0;
                    while (x<this.columnNames.length) {
                        if (this.indexes[x]!=null) {
                            String temp_String=newRowDetails[x];
                            this.indexes[x].insert(new Cell(k,temp_String));
                        }
                        this.database[k][x]=newRowDetails[x];
                        x+=1;
                    }
                }
                catch (DatabaseException exception) {
                    throw exception;
                }
            } else {
                throw DatabaseException.databaseFull();
            }
        }
    }

    public String[] new_array(String[] array) {
        // TODO: Implementation
        String[] copy=new String[array.length];
        int i=0;
        while (i<array.length) {
            copy[i]=array[i];
            i+=1;
        }
        return copy;
    }

    public String[][] removeAllWhere(String col, String data) throws DatabaseException {
        // TODO: Implementation
        int k=0;
        while (k<this.columnNames.length) {
            if (this.columnNames[k].equals(col)) {
                break;
            }
            k+=1;
        }
        if (k<this.columnNames.length) {
            try {
                int index=0;
                String[][] store_removed_data=new String[this.database.length][this.columnNames.length];
                int i=0;
                while (i<this.database.length) {
                    store_removed_data[index]=new_array(removeFirstWhere(col, data));
                    if (store_removed_data[index].length!=0) {
                        index+=1;
                    }
                    i+=1;
                }
                if (index==0) {
                    return new String[0][0];
                } else {
                    String[][] removeAllWhere=new String[index][this.columnNames.length];
                    i=0;
                    while (i<index) {
                        removeAllWhere[i]=new_array(store_removed_data[i]);
                        i+=1;
                    }
                    return removeAllWhere;
                }
            }
            catch (DatabaseException exception) {
                throw exception;
            }
        } else {
            throw DatabaseException.invalidColumnName(col);
        }
    }

    public String[] removeFirstWhere(String col, String data) throws DatabaseException {
        // TODO: Implementation
        try {
            int k=-1;
            try {
                k=countOccurences(col, data);
                if (k>0) {
                    k=0;
                    while (k<this.columnNames.length && !(this.columnNames[k].compareTo(col)==0)) {
                        k+=1;
                    }
                } else {
                    k=-1;
                }
            }
            catch (DatabaseException exception) {
                throw exception;
            }
            if (k==-1) {
                return new String[0];
            } else {
                if (k<this.columnNames.length) {
                    if (this.indexes[k]==null) {
                        int i=0;
                        while (i<this.database.length) {
                            if (this.database[i][k]!=null && this.database[i][k].compareTo(data)==0) {
                                String[] remove=new_array(this.database[i]);
                                int t=0;
                                while (t<this.columnNames.length) {
                                    this.database[i][t]=null;
                                    if (this.indexes[t]!=null) {
                                        String temp_String=remove[t];
                                        this.indexes[t].remove(new Cell(i,temp_String));
                                    }
                                    t++;
                                }
                                return remove;
                            }
                            i+=1;
                        }
                    } else {
                        Node<Cell> removed_node=this.indexes[k].remove(new Cell(1,data));
                        if (removed_node!=null) {
                            int index=removed_node.getData().databaseRow;
                            String[] remove=new_array(this.database[index]);
                            int t=0;
                            while (t<this.columnNames.length) {
                                this.database[index][t]=null;
                                if (this.indexes[t]!=null) {
                                    String temp_String=remove[t];
                                    this.indexes[t].remove(new Cell(index,temp_String));
                                }
                                t++;
                            }
                            return remove;
                        }
                    }
                }
            }
        }
        catch (DatabaseException exception) {
            throw exception;
        }
        return new String[0];
    }

    public String[][] findAllWhere(String col, String data) throws DatabaseException {
        // TODO: Implementation
        int k=0;
        while (k<this.columnNames.length && this.columnNames[k].compareTo(col)!=0) {
            k+=1;
        }
        if (k<this.columnNames.length) {
            int index=0;
            String[][] store_removed_data=new String[this.database.length][this.columnNames.length];
            int i=0;
            while (i<this.database.length) {
                if (this.database[i][k]!=null && this.database[i][k].compareTo(data)==0) {
                    if (this.indexes[k]!=null) {
                        Node<Cell> extract_find_index=this.indexes[k].access(new Cell(i,data));
                        if (extract_find_index!=null) {
                            int finding_nodes=extract_find_index.getData().databaseRow;
                            store_removed_data[index]=new_array(this.database[finding_nodes]);
                            if (store_removed_data[index].length!=0) {
                                index+=1;
                            }
                        }
                    } else {
                        store_removed_data[index]=new_array(this.database[i]);
                        if (store_removed_data[index].length!=0) {
                            index+=1;
                        }
                    }
                }
                i+=1;
            }
            if (index==0) {
                return new String[0][0];
            } else {
                String[][] removeAllWhere=new String[index][this.columnNames.length];
                i=0;
                while (i<index) {
                    removeAllWhere[i]=new_array(store_removed_data[i]);
                    i+=1;
                }
                return removeAllWhere;
            }
        } else {
            throw DatabaseException.invalidColumnName(col);
        }
    }

    public String[] findFirstWhere(String col, String data) throws DatabaseException {
        // TODO: Implementation
        int k=0;
        while (k<this.columnNames.length && !this.columnNames[k].equals(col)) {
            k+=1;
        }
        if (k<this.columnNames.length) {
            if (this.indexes[k]==null) {
                int i=0;
                while (i<this.database.length) {
                    if (this.database[i][k]!=null && this.database[i][k].compareTo(data)==0) {
                        return new_array(this.database[i]);
                    }
                    i+=1;
                }
            } else {
                Node<Cell> extract_find_index=this.indexes[k].access(new Cell(1,data));
                if (extract_find_index!=null) {
                    int index=extract_find_index.getData().databaseRow;
                    return new_array(this.database[index]);
                }
            }
            return new String[0];
        } else {
            throw DatabaseException.invalidColumnName(col);
        }
    }

    public String[][] updateAllWhere(String col, String updateCondition, String data) throws DatabaseException {
        // TODO: Implementation
        int k=0;
        while (k<this.columnNames.length) {
            if (this.columnNames[k].compareTo(col)==0) {
                break;
            }
            k+=1;
        }
        if (k<this.columnNames.length) {
            try {
                String[][] store_removed_data=new String[this.database.length][this.columnNames.length];
                int index=0, i=0;
                while (i<this.database.length) {
                    store_removed_data[index]=new_array(updateFirstWhere(col, updateCondition, data));
                    if (store_removed_data[index].length!=0) {
                        index+=1;
                    }
                    i+=1;
                }
                if (index>0 || index<0) {
                    String[][] removeAllWhere=new String[index][this.columnNames.length];
                    i=0;
                    while (i<index) {
                        removeAllWhere[i]=new_array(store_removed_data[i]);
                        i+=1;
                    }
                    return removeAllWhere;
                } else {
                    return new String[0][0];
                }
            }
            catch (DatabaseException exception) {
                throw exception;
            }
        }
        else throw DatabaseException.invalidColumnName(col);
    }

    public String[] updateFirstWhere(String col, String updateCondition, String data) throws DatabaseException {
        // TODO: Implementation
        int k=0;
        while (k<this.columnNames.length && !this.columnNames[k].equals(col)) {
            k+=1;
        }
        if (k<this.columnNames.length) {
            if (this.indexes[k]==null) {
                int i=0;
                while (i<this.database.length) {
                    if (this.database[i][k]!=null && this.database[i][k].compareTo(updateCondition)==0) {
                        this.database[i][k]=data;
                        return new_array(this.database[i]);
                    }
                    i+=1;
                }
            } else {
                try {
                    Node<Cell> removed_node=this.indexes[k].remove(new Cell(1,updateCondition));
                    if (removed_node!=null) {
                        Cell cell_object=removed_node.getData();
                        this.database[cell_object.databaseRow][k]=data;
                        String[] update=new_array(this.database[cell_object.databaseRow]);
                        this.indexes[k].insert(new Cell(cell_object.databaseRow,data));
                        return update;
                    }
                }
                catch (DatabaseException exception) {
                    throw exception;
                }
            }
            String[] return_array=new String[0];
            return return_array;
        } else {
            throw DatabaseException.invalidColumnName(col);
        }
    }

    public Treap<Cell> generateIndexOn(String col) throws DatabaseException {
        // TODO: Implementation
        int k=0;
        while (k<this.columnNames.length && !(this.columnNames[k].compareTo(col)==0)) k+=1;
        if (k<this.columnNames.length) {
            if (this.indexes[k]==null) {
                Treap<Cell> myTreap=new Treap<>();
                int x=0;
                this.indexes[k]=myTreap;
                while (x<this.database.length) {
                    if (this.database[x][k]!=null) {
                        String temporary_string=this.database[x][k];
                        try {
                            this.indexes[k].insert(new Cell(x,temporary_string));
                        }
                        catch (DatabaseException exception) {
                            this.indexes[k]=null;
                            throw exception;
                        }
                    }
                    x+=1;
                }
            }
        } else {
            throw DatabaseException.invalidColumnName(col);
        }
        return this.indexes[k];
    }

    public Treap<Cell>[] generateIndexAll() throws DatabaseException {
        // TODO: Implementation
        int k=0;
        while (k<this.columnNames.length) {
            try {
                this.indexes[k]=generateIndexOn(this.columnNames[k]);
            }
            catch (DatabaseException exception) {
            }
            k+=1;
        }
        return this.indexes;
    }

    public int countOccurences(String col, String data) throws DatabaseException {
        // TODO: Implementation
        int k=0;
        while (k<this.columnNames.length) {
            if (this.columnNames[k].compareTo(col)==0) {
                break;
            }
            k+=1;
        }
        if (k<this.columnNames.length) {
            int counter_var=0;
            int x=0;
            while (x<this.database.length) {
                if (this.database[x][k]!=null) {
                    if (this.database[x][k]==data) {
                        counter_var+=1;
                    }
                }
                x+=1;
            }
            return counter_var;
        } else {
            throw DatabaseException.invalidColumnName(col);
        }
    }
}