package br.com.francivaldo;

import java.util.ArrayList;
import java.util.Collection;
public class QuadTree<E extends Vector2>{
    private Collection<E> list;
    private Rectangle rec;
    private int capacity;
    private QuadTree[] qt;
    public QuadTree(Rectangle rec){
        this.list = new ArrayList<E>();
        this.rec = rec;
        this.capacity = 4;
    }
    public QuadTree(Rectangle rec,int capacity){
        this.list = new ArrayList<E>();
        this.rec = rec;
        this.capacity = capacity;
    }
    public QuadTree(Rectangle rec,Collection<E> collection){
        this.list = new ArrayList<E>();
        this.rec = rec;
        this.capacity = 4;
        LoadList(collection);
    }
    public QuadTree(Rectangle rec,int capacity,Collection<E> collection){
        this.list = new ArrayList<E>();
        this.rec = rec;
        this.capacity = capacity;
        LoadList(collection);
    }
    private void LoadList(Collection<E> collection){
        for (E e : collection) {
            add(e);
        }
    }
    private void Divide(){
        int x,y,w,h;
        x = this.rec.x;
        y = this.rec.y;
        w = this.rec.w/2;
        h = this.rec.h/2;
        qt = new QuadTree[4];
        qt[0] = new QuadTree<E>(new Rectangle(x,y,w, h));
        qt[1] = new QuadTree<E>(new Rectangle(x+w,y,w, h));
        qt[2] = new QuadTree<E>(new Rectangle(x,y+h,w, h));
        qt[3] = new QuadTree<E>(new Rectangle(x+w,y+h,w, h));
    }
    public Collection<E> getList(){
        Collection<E> res = new ArrayList<E>();
        for (E e : list) {
            res.add(e);
        }
        if(qt != null)
            for (QuadTree e : qt) {
                res = Concat(res, e.getList());
            }
        return res;
    }
    private Collection<E> Concat(Collection<E> n0,Collection<E> n1){
        Collection<E> res = new ArrayList<E>();
        for (E e : n0) {
            res.add(e);
        }
        for (E e : n1) {
            res.add(e);
        }
        return res;
    }
    public Collection<E> query(Rectangle rec){
        Collection<E> res = new ArrayList<E>();
        if(rec.Contains(rec)){
            for (E e : list) {
                if(rec.Contains(e))
                    res.add(e);
            }
            if(qt != null)
                for (QuadTree e : qt) {
                    res = Concat(res, e.query(rec));
                }
        }
        return res;
    }
    public boolean add(E it){
        if(list.size()<capacity){
            if(rec.Contains(new Rectangle(it.x, it.y, 0, 0))){
                list.add(it);
                return true;
            }
            return false;
        }
        if(qt == null)
            Divide();
        for (QuadTree e : qt) {
            boolean res = e.add(it);
            if(res)
                return res;
        }
        
        return false;
    }
    }
