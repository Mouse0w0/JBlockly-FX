package team.unstudio.jblockly;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public interface BlockGlobal {

	double INSERT_WIDTH = 5;
	double INSERT_OFFSET_Y = 10;
	double INSERT_HEIGHT = 10;
	
	double NEXT_WIDTH = 10;
	double NEXT_HEIGHT = 5;
	double NEXT_OFFSET_X = 10;
	
	double BLOCK_SLOT_WIDTH = 5;
	double BLOCK_SLOT_HEIGHT = 30;
	double INSERT_SLOT_WIDTH = INSERT_WIDTH;
	double INSERT_SLOT_HEIGHT = INSERT_OFFSET_Y+INSERT_HEIGHT+10;
	double NEXT_SLOT_WIDTH = NEXT_OFFSET_X+NEXT_WIDTH;
	double NEXT_SLOT_HEIGHT = NEXT_HEIGHT;
	
	Bounds DEFAULT_SLOT_BOUNDS = new BoundingBox(0, 0, 5, 25);
	Bounds INSERT_SLOT_BOUNDS = new BoundingBox(0, 0, INSERT_WIDTH, INSERT_OFFSET_Y+INSERT_HEIGHT);
	Bounds NEXT_SLOT_BOUNDS = new BoundingBox(0, 0 , NEXT_OFFSET_X+NEXT_WIDTH, NEXT_HEIGHT);
	
	Bounds INSERT_SLOT_LINK_BOUNDS = new BoundingBox(-5, INSERT_OFFSET_Y, INSERT_WIDTH+5, INSERT_HEIGHT);
	Bounds NEXT_SLOT_LINK_BOUNDS = new BoundingBox(NEXT_OFFSET_X,-5,NEXT_WIDTH,NEXT_HEIGHT+5);
	
	double BLOCK_SLOT_MIN_LINE_WIDTH = 100;
	double BRANCH_SLOT_BOTTOM_HEIGHT = 10;
	double BRANCH_SLOT_CONTAINER_MIN_HEIGHT = 10;
	double NEXT_SLOT_BOTTOM_HEIGHT = 10;
	double BRANCH_MIN_WIDTH = 20;
}
