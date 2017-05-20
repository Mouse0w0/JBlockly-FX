package team.unstudio.jblockly;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import team.unstudio.jblockly.Block.ConnectionType;

public class BlockSlot extends Region {

	public static final Bounds INSERT_SLOT_BOUNDS = new BoundingBox(0, Block.INSERT_OFFSET_Y, Block.INSERT_WIDTH, Block.INSERT_HEIGHT);
	public static final Bounds NEXT_SLOT_BOUNDS = new BoundingBox(Block.NEXT_OFFSET_X,0,Block.NEXT_WIDTH,Block.NEXT_HEIGHT+5);
	public static final double BLOCK_SLOT_MIN_WIDTH=0;
	public static final double BLOCK_SLOT_MIN_HEIGHT=30;
	public static final double BRANCH_MIN_WIDTH = 20;
	
	public enum SlotType {
		NONE, INSERT, BRANCH, NEXT
	}

	private SlotType slotType;
	private Block block;

	public BlockSlot() {
		this(SlotType.NONE);
	}

	public BlockSlot(SlotType slotType) {
		this.slotType = slotType;
	}
	
	public BlockSlot(SlotType slotType,Block block) {
		this(slotType);
		setBlock(block);
	}

	public BlockWorkspace getWorkspace() {
		Parent parent = getParent();

		if (parent instanceof Block)
			return ((Block) parent).getWorkspace();
		else
			return null;
	}

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	public Block getBlock() {
		return block;
	}
	
	public boolean hasBlock(){
		return block != null;
	}

	public boolean setBlock(Block block) {
		if(!isCanLinkBlock(block))
			return false;
		
		if (this.block != null)
			this.block.addToWorkspace();
		if (block != null)
			getChildren().add(block);
		this.block = block;
		return true;
	}

	public void validateBlock() {
		if (block != null && block.getParent() != this)
			block = null;
	}
	
	public boolean tryLinkBlock(Block block,double x,double y){
		switch (getSlotType()) {
		case INSERT:
			if(INSERT_SLOT_BOUNDS.contains(x, y))
				return setBlock(block);
			break;
		case NEXT:
			if(NEXT_SLOT_BOUNDS.contains(x, y))
				return setBlock(block);
			break;
		case BRANCH:
			if(NEXT_SLOT_BOUNDS.contains(x, y))
				return setBlock(block);
			break;
		default:
			break;
		}
		
		if(hasBlock()&&contains(x, y))
			return this.block.tryLinkBlock(block, x, y);
		
		return false;
	}
	
	public boolean isCanLinkBlock(Block block){
		ConnectionType connectionType = block.getConnectionType();
		switch (getSlotType()) {
		case NEXT:
		case BRANCH:
			if(connectionType==ConnectionType.TOP||connectionType==ConnectionType.TOPANDBOTTOM)
				return true;
			else 
				return false;
		case INSERT:
			if(connectionType==ConnectionType.LEFT)
				return true;
			else
				return false;
		default:
			return false;
		}
	}

	@Override
	protected void layoutChildren() {
		if (hasBlock())
			layoutInArea(block, 0, 0, prefWidth(-1), prefHeight(-1), 0, null, HPos.CENTER, VPos.CENTER);
	}
	

	@Override
	protected double computePrefWidth(double height) {
		if(hasBlock())
			return block.prefWidth(height);
		
		switch (getSlotType()) {
		case INSERT:
			return INSERT_SLOT_BOUNDS.getMaxX()+INSERT_SLOT_BOUNDS.getWidth();
		case BRANCH:
		case NEXT:
			return NEXT_SLOT_BOUNDS.getMaxX()+NEXT_SLOT_BOUNDS.getWidth();
		case NONE:
		default:
			return BLOCK_SLOT_MIN_WIDTH;
		}
	}

	@Override
	protected double computePrefHeight(double width) {
		if(hasBlock())
			return block.prefHeight(width);
		
		switch (getSlotType()) {
		case INSERT:
			return INSERT_SLOT_BOUNDS.getMaxY()+INSERT_SLOT_BOUNDS.getHeight();
		case BRANCH:
		case NEXT:
			return NEXT_SLOT_BOUNDS.getMaxY()+NEXT_SLOT_BOUNDS.getHeight();
		case NONE:
		default:
			return BLOCK_SLOT_MIN_HEIGHT;
		}
	}
	
	private double lineWidth = 0, lineHeight = 0;
	private int firstNode = 0, lastNode = 0;

	double getLineHeight() {
		return lineHeight;
	}

	void setLineHeight(double lineHeight) {
		this.lineHeight = lineHeight;
	}
	
	double getOriginalLineWidth(){
		return lineWidth;
	}
	
	double getLayoutLineWidth(){
		switch (slotType) {
		case BRANCH:
			return lineWidth<BRANCH_MIN_WIDTH?BRANCH_MIN_WIDTH:lineWidth;
		default:
			return lineWidth;
		}
	}

	double getLineWidth() {
		switch (slotType) {
		case BRANCH:
			return lineWidth<BRANCH_MIN_WIDTH?BRANCH_MIN_WIDTH:lineWidth;
		case INSERT:
			return lineWidth+Block.INSERT_WIDTH;
		default:
			return lineWidth;
		}
	}

	void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	int getFirstNode() {
		return firstNode;
	}

	void setFirstNode(int firstNode) {
		this.firstNode = firstNode;
	}

	int getLastNode() {
		return lastNode;
	}

	void setLastNode(int lastNode) {
		this.lastNode = lastNode;
	}
}