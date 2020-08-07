package tests;

import static org.junit.Assert.*;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess_package.Bishop_Class;
import chess_package.Board_Class;
import chess_package.King_Class;
import chess_package.Knight_Class;
import chess_package.Piece_Class;
import chess_package.Queen_Class;
import chess_package.Rook_Class;

class Chess960Tests {
	
	Board_Class board = new Board_Class();
	
	@BeforeEach
	void SetUp()
	{
		board.set_chess_board(true);
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
	
	@Test
	void SurroundedByRooks()
	{
		int[] places = {-1, -1, -1};
		
		for(int i = 0; i < 8; i++)
		{
			Piece_Class piece = board.get_loc_piece(0, i);
			if(piece instanceof Rook_Class)
			{
				if(places[0] == -1)
				{
					places[0] = i;
				}
				else {
					places[2] = i;
				}
			}
			else if (piece instanceof King_Class)
			{
				places[1] = i;
			}
		}
		
		assertTrue(places[0] < places[1] && places[1] < places[2]);
	}
	
	@Test
	void ProperBishopPlacement()
	{
		//One should be on an odd space, the other on an even
		ArrayList<Integer> rookPlaces = new ArrayList<Integer>();
		boolean evenPlace = false;
		boolean oddPlace = false;
		
		for(int i = 0; i < 8; i++)
		{
			Piece_Class piece = board.get_loc_piece(0, i);
			if(piece instanceof Bishop_Class)
			{
				rookPlaces.add(i);
			}
		}
		
		if(rookPlaces.size() > 2)
		{
			fail();
		}
		
		for (int place : rookPlaces)
		{
			if(place % 2 == 0)
			{
				evenPlace = true;
			}
			else if (place % 2 == 1)
			{
				oddPlace = true;
			}
		}
		
		assertTrue(evenPlace && oddPlace);
	}
	
	@Test
	void ProperPieceAmounts()
	{
		int[] piecesFound = new int[5];
		int[] pieceAmounts = {1, 1, 2, 2, 2}; //King, Queen, Bishop, Knight, Rook
		
		for(int i = 0; i < 8; i++) {
			Piece_Class piece = board.get_loc_piece(0, i);
			if(piece instanceof King_Class)
			{
				piecesFound[0] += 1;
			}
			else if (piece instanceof Queen_Class)
			{
				piecesFound[1] += 1;
			}
			else if (piece instanceof Bishop_Class)
			{
				piecesFound[2] += 1;
			}
			else if (piece instanceof Knight_Class)
			{
				piecesFound[3] += 1;
			}
			else if (piece instanceof Rook_Class)
			{
				piecesFound[4] += 1;
			}
		}
		
		assertArrayEquals(piecesFound, pieceAmounts);
	}

}
