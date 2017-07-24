package com.vijai;

public class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", name=" + name + "] ";
    }


    @Override
    public boolean equals(Object o){

        if(o==null)
            return false;
        if(this.getClass()!=o.getClass())
            return false;

        User e=(User)o;
        return e.id.equals(this.id) ;//let's allow name replacing, check id only
    }

    @Override
    public int hashCode(){
        if (id != null) {
            return id.hashCode(); //let's allow  name replacing, check id only
        } else {
            return 0;
        }
    }

}
