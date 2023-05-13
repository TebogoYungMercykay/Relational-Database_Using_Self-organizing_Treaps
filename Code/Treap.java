// Selepe Sello => TebogoYungMercykay
public class Treap<T extends Comparable<T>> {
    public Node<T> root=null;

    @Override
    public String toString() {
        if (this.root==null) {
            return "";
        }
        return this.root.toString() + "\n" + toString(this.root, "");
    }

    private String toString(Node<T> curr, String pre) {
        if (curr==null)
            return "";
        String res="";

        if (curr.left!=null) {
            if (curr.right!=null) {
                res+=pre + "├(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "|    ");
            } else {
                res+=pre + "└(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "     ");
            }
        }
        if (curr.right!=null) {
            res+=pre + "└(R)─ " + curr.right.toString() + "\n" + toString(curr.right, pre + "   ");
        }
        return res;
    }

    public Node<T> access(T data) {
        Node<T> access_object = node_already_exists(data);
        if (access_object!=null) {
            Node<T> iterator_object=this.root;
            Node<T> remove_object=null;
            while (true) {
                if (iterator_object.data.equals(data)) {
                    int priority_value=iterator_object.priority+1;
                    iterator_object.priority=priority_value;
                    break;
                } else {
                    if (iterator_object.data.equals(data)==false && data.compareTo(iterator_object.data)<0 && iterator_object.left!=null) {
                        Node<T> temp_object=iterator_object.left;
                        iterator_object=temp_object;
                    } else if (iterator_object.data.equals(data)==false && data.compareTo(iterator_object.data)>0 && iterator_object.right!=null) {
                        Node<T> temp_object=iterator_object.right;
                        iterator_object=temp_object;
                    } else {
                        return remove_object;
                    }
                }
            }
            Node<T> parentObject=this.get_parent_node(this.root,iterator_object.data);
            if(iterator_object!=this.root && parentObject!=null) {
                int first_priority=iterator_object.priority;
                int second_priority=parentObject.priority;
                while(iterator_object!=this.root && parentObject!=null && first_priority>=second_priority) {
                    if (data.compareTo(parentObject.data)>=0) {
                        this.perform_left_rotation(parentObject);
                    } else {
                        this.perform_right_rotation(parentObject);
                    }
                    parentObject=this.get_parent_node(this.root,data);
                    if(iterator_object!=this.root && parentObject!=null) {
                        first_priority=iterator_object.priority;
                        second_priority=parentObject.priority;
                    }
                }
            }
            return iterator_object;
        }
        return access_object;
    }

    public void insert(T data) throws DatabaseException {
        if (node_already_exists(data)!=null) {
            throw DatabaseException.duplicateInsert(data);
        } else if (empty_treap()) {
            this.root=new Node<T>(data);
        } else {
            Node<T> insert_object,removed_data;
            insert_object=new Node<T>(data);
            removed_data=this.root;
            while (true) {
                Node<T> left_node=removed_data.left;
                Node<T> right_node=removed_data.right;
                T temp_data=removed_data.data;
                if (temp_data.compareTo(data)>0 && left_node!=null) {
                    removed_data=left_node;
                } else if (temp_data.compareTo(data)<0 && right_node!=null) {
                    removed_data=right_node;
                } else {
                    temp_data=removed_data.data;
                    if (data.compareTo(temp_data)<=0) {
                        removed_data.left=null;
                        removed_data.left=insert_object;
                        break;
                    } else {
                        removed_data.right=null;
                        removed_data.right=insert_object;
                        break;
                    }
                }
            }
            Node<T> parentObject=this.get_parent_node(this.root,data);
            if (insert_object!=this.root && parentObject!=null) {
                int first_priority=insert_object.priority;
                int second_priority=parentObject.priority;
                while (insert_object!=this.root && parentObject!=null && first_priority>=second_priority) {
                    if (parentObject.data.compareTo(data)>=0) {
                        this.perform_right_rotation(parentObject);
                    } else {
                        this.perform_left_rotation(parentObject);
                    }
                    parentObject=this.get_parent_node(this.root,data);
                    if (insert_object!=this.root && parentObject!=null) {
                        first_priority=insert_object.priority;
                        second_priority=parentObject.priority;
                    }
                }
            }
        }
    }

    public boolean empty_treap() {
        boolean res_var=(this.root==null);
        return res_var;
    }

    public Node<T> remove(T data) {
        Node<T> removed_data=null;
        if (empty_treap()==false) {
            removed_data=node_already_exists(data);
            if (removed_data!=null) {
                while(removed_data.left!=null && removed_data.right!=null) {
                    Node<T> temp_object=removed_data.left;
                    Node<T> temp_object2=removed_data.right;
                    if (temp_object.priority>temp_object2.priority) {
                        this.perform_right_rotation(removed_data);
                    } else {
                        this.perform_left_rotation(removed_data);
                    }
                }
                Node<T> parentObject,rotation_object;
                parentObject=this.get_parent_node(this.root, data);
                rotation_object=null;
                if (removed_data.left!=null) {
                    Node<T> temp_object=removed_data.left;
                    rotation_object=temp_object;
                } else {
                    Node<T> temp_object=removed_data.right;
                    rotation_object=temp_object;
                }
                if (parentObject!=null && removed_data.data.compareTo(parentObject.data)>=0) {
                    Node<T> temp_object=rotation_object;
                    parentObject.right=temp_object;
                } else if (parentObject!=null) {
                    parentObject.left=rotation_object;
                } else {
                    this.root=rotation_object;
                }
                return removed_data;
            }
        }
        return removed_data;
    }

    private Node<T> get_parent_node(Node<T> ptrObject, T data) {
        if (ptrObject==null) {
            return null;
        } else {
            Node<T> left_node=ptrObject.left;
            Node<T> right_node=ptrObject.right;
            boolean double_check1=(left_node!=null && data.compareTo(left_node.data)==0);
            boolean double_check2=(right_node!=null && data.compareTo(right_node.data)==0);
            if (double_check1 || double_check2) {
                return ptrObject;
            } else if (data.compareTo(ptrObject.data) != 0 && data.compareTo(ptrObject.data)<0) {
                return this.get_parent_node(left_node, data);
            } else {
                return this.get_parent_node(right_node, data);
            }
        }
    }

    public Node<T> node_already_exists(T data) {
        Node<T> search_node = null;
        if(empty_treap() || data==search_node) {
            return search_node;
        } else {
            search_node=this.root;
            while (true) {
                T temp_data=search_node.data;
                if (temp_data!=null && data.compareTo(temp_data)==0) {
                    return search_node;
                } else {
                    if (data.compareTo(temp_data)!=0 && data.compareTo(temp_data)<0 && search_node.left!=null) {
                        search_node=search_node.left;
                    } else if (data.compareTo(temp_data)!=0 && data.compareTo(temp_data)>0 && search_node.right!=null) {
                        search_node=search_node.right;
                    } else {
                        search_node = null;
                        return search_node;
                    }
                }
            }
        }
    }

    public void perform_right_rotation(Node<T> ptrObject) {
        if (ptrObject!=null) {
            Node<T> childObject,parentObject;
            childObject=ptrObject.left;
            T temp_data=ptrObject.data;
            parentObject=this.get_parent_node(this.root,temp_data);
            if (parentObject==null) {
                this.root=childObject;
            } else {
                T temp_data2=parentObject.data;
                if (parentObject!=null && temp_data.compareTo(temp_data2)<0) {
                    parentObject.left=childObject;
                } else {
                    parentObject.right=childObject;
                }
            }
            Node<T> temp_object=childObject.right;
            ptrObject.left=temp_object;
            Node<T> temp_object1=ptrObject;
            childObject.right=temp_object1;
        }
    }

    public void perform_left_rotation(Node<T> ptrObject) {
        if (ptrObject!=null) {
            Node<T> childObject,parentObject;
            childObject=ptrObject.right;
            T temp_data=ptrObject.data;
            parentObject=this.get_parent_node(this.root,temp_data);
            if (parentObject==null) {
                this.root=childObject;
            } else {
                T temp_data2=parentObject.data;
                if (parentObject!=null && temp_data.compareTo(temp_data2)<0) {
                    parentObject.left=childObject;
                } else {
                    parentObject.right=childObject;
                }
            }
            Node<T> temp_object=childObject.left;
            ptrObject.right=temp_object;
            Node<T> temp_object1=ptrObject;
            childObject.left=temp_object1;
        }
    }
}