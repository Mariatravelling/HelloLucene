package Message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="temp_index")
public class TempIndex {

	public final static int ADD_OP=1;
	public final static int DELETE_OP=2;
	public final static int UPDATE_OP=3;
	
	private int id;
	private int objId;
	private String type;
	private int operator;
	
	public void setAdd()
	{
		operator=ADD_OP;
	}
	
	public void setDelete()
	{
		operator=DELETE_OP;
	}
	
	public void setUpdate()
	{
		operator=UPDATE_OP;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * the id of operated object
	 * @return
	 */
	@Column(name="obj_id")
	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	/**
	 * The type of operated Object
	 * @return
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * the type of operators(delete,update,add)
	 * @return
	 */
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	
	
}
