package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BLOCK_MASTER")    
public class Block extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Block_id")
	private Long blockId;
	@Column(name = "Block")
	private String block;
	
	public Long getBlockId() {
		return blockId;
	}
	
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

}
