package cigo.analysis.persistence.compositeid;

import java.io.Serializable;

/**
 * Interface for class representing composite primary key
 * <b>All primary key - class  must implements this interface</b>
 * @author FBiancardi, ATavanti
 *
 */
public interface ICompositePk extends Cloneable,Serializable{

	/**
	 * deep copy of an object
	 * @return the copied object
	 * @throws CloneNotSupportedException
	 */
	public  Object copyObject() throws CloneNotSupportedException;
	public  int hashCode();
	public  boolean equals(Object o);
}
