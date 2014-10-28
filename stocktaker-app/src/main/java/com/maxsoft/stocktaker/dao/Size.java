package com.maxsoft.stocktaker.dao;

import com.maxsoft.stocktaker.dao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table SIZE.
 */
public class Size {

    private Long id;
    private Integer pieces;
    private Integer quantityPerPiece;
    private Long unitID;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient SizeDao myDao;

    private Unit unit;
    private Long unit__resolvedKey;


    public Size() {
    }

    public Size(Long id) {
        this.id = id;
    }

    public Size(Long id, Integer pieces, Integer quantityPerPiece, Long unitID) {
        this.id = id;
        this.pieces = pieces;
        this.quantityPerPiece = quantityPerPiece;
        this.unitID = unitID;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSizeDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    public Integer getQuantityPerPiece() {
        return quantityPerPiece;
    }

    public void setQuantityPerPiece(Integer quantityPerPiece) {
        this.quantityPerPiece = quantityPerPiece;
    }

    public Long getUnitID() {
        return unitID;
    }

    public void setUnitID(Long unitID) {
        this.unitID = unitID;
    }

    /** To-one relationship, resolved on first access. */
    public Unit getUnit() {
        Long __key = this.unitID;
        if (unit__resolvedKey == null || !unit__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UnitDao targetDao = daoSession.getUnitDao();
            Unit unitNew = targetDao.load(__key);
            synchronized (this) {
                unit = unitNew;
            	unit__resolvedKey = __key;
            }
        }
        return unit;
    }

    public void setUnit(Unit unit) {
        synchronized (this) {
            this.unit = unit;
            unitID = unit == null ? null : unit.getId();
            unit__resolvedKey = unitID;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
