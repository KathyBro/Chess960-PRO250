package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess_package.Board_Class;
import chess_package.King_Class;
import chess_package.Piece_Class;

class Chess960Tests {
	
	Board_Class board = new Board_Class();
	
	@BeforeEach
	void SetUp()
	{
		board.set_chess_board(true);
	}
	
	@Test
	void checkTest() 
	{
		assertTrue(true);
	}
	
	@Test
	void OppositeKingPlacement() {
//		Board_Class board = new Board_Class();
//		board.set_chess_board(true);
		//Kings should be placed between 1 and 6
		boolean foundKings = false;
		
		for(int i = 1; i < 7; i++)
		{
			Piece_Class piece = board.get_loc_piece(0, i);
			if(piece instanceof King_Class)
			{
				if(board.get_loc_piece(7, i) instanceof King_Class)
				{
					foundKings = true;					
				}
			}
		}
		
		assertTrue(foundKings);
	}

	@Test
	void WhiteKingPlacement() {
//		Board_Class board = new Board_Class();
//		board.set_chess_board(true);
		
		//Kings should be placed between 1 and 6
		boolean foundKing = false;
		
		for(int i = 1; i < 7; i++)
		{
			Piece_Class piece = board.get_loc_piece(0, i);
			if(piece instanceof King_Class)
			{
				foundKing = true;
			}
		}
		
		assertTrue(foundKing);
	}
	

}
