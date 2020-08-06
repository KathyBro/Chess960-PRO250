package chess_package; //chess package

import java.util.Random;

public class Board_Class { // Board class

	Piece_Class array_2d_piece[][] = new Piece_Class[8][8];

	static String player_color = "black";

	public void set_chess_board(boolean chess960) { // set the pieces in the board

		Random rng = new Random();
		int position = 1 + rng.nextInt(6);

		King_Class king_obj = new King_Class("white", 'k', "king");
		King_Class black_king_obj = new King_Class("black", 'K', "king");

		if (chess960) {
			array_2d_piece[0][position] = king_obj;
			array_2d_piece[7][position] = black_king_obj;
		}

		//Pawns are the same set up for either version
		for (int i = 0; i < 8; i++) {
			Pawn_Class pawn_obj = new Pawn_Class("white", 'p', "pawn"); // white pawn
			array_2d_piece[1][i] = pawn_obj;
		}

		for (int i = 0; i < 8; i++) {
			Pawn_Class pawn_obj = new Pawn_Class("black", 'P', "pawn"); // black pawn
			array_2d_piece[6][i] = pawn_obj;
		}

		if (!chess960) { //If it isn't 960, set like normal chess
			king_obj = new King_Class("white", 'k', "king");
			black_king_obj = new King_Class("black", 'K', "king");
			array_2d_piece[0][3] = king_obj; // white king initialization
			array_2d_piece[7][3] = black_king_obj; // black king initialization

			for (int i = 0; i <= 7; i += 7) {
				Rook_Class rook_obj = new Rook_Class("white", 'r', "rook");
				array_2d_piece[0][i] = rook_obj; // white Rook initialization
			}

			for (int i = 1; i <= 6; i += 5) {
				Knight_Class knight_obj = new Knight_Class("white", 'n', "knight");
				array_2d_piece[0][i] = knight_obj; // white knight initialization
			}

			for (int i = 2; i <= 5; i+=3) {
				Bishop_Class bishop_obj = new Bishop_Class("white", 'b', "bishop");
				array_2d_piece[0][i] = bishop_obj; // white bishop initialization
			}

			// black piece initialization

			for (int i = 0; i <= 7; i += 7) {
				Rook_Class rook_obj = new Rook_Class("black", 'R', "rook");
				array_2d_piece[7][i] = rook_obj; // Rook initialization
			}

			for (int i = 1; i <= 6; i += 5) {
				Knight_Class knight_obj = new Knight_Class("black", 'N', "knight");
				array_2d_piece[7][i] = knight_obj; // black knight initialization
			}

			for (int i = 2; i <= 5; i+=3) {
				Bishop_Class bishop_obj = new Bishop_Class("black", 'B', "bishop");
				array_2d_piece[7][i] = bishop_obj; // black bishop initialization
			}

			Queen_Class queen_obj = new Queen_Class("white", 'q', "queen"); // white queen initialization
			array_2d_piece[0][4] = queen_obj;

			Queen_Class black_queen_obj = new Queen_Class("black", 'Q', "queen"); // black queen initialization
			array_2d_piece[7][4] = black_queen_obj;
		}
		else if (chess960) //If it is chess960, then we'll need to do random positions
		{
			//We'll start with the rook because it's dependent on the king
			
			//to the right of the king
			int newposition = rng.nextInt((7-position)) + position + 1;
			
			Rook_Class rook_obj = new Rook_Class("white", 'r', "rook");
			array_2d_piece[0][newposition] = rook_obj; // white Rook initialization
			Rook_Class b_rook_obj = new Rook_Class("black", 'R', "rook");
			array_2d_piece[7][newposition] = b_rook_obj; // Rook initialization
			
			//to the left of the king
			newposition = rng.nextInt(position);
			rook_obj = new Rook_Class("white", 'r', "rook");
			array_2d_piece[0][newposition] = rook_obj; // white Rook initialization
			b_rook_obj = new Rook_Class("black", 'R', "rook");
			array_2d_piece[7][newposition] = b_rook_obj; // Rook initialization
			
			
			//Next, we'll set bishops because they have specific rules as well.
			newposition = rng.nextInt(8);
			
			//We'll place them on an even space
			boolean placed = false;
			
			while(!placed) {
				if(array_2d_piece[7][newposition] == null) //This means that the position is open
				{
					if(newposition % 2 == 0) { //This tells us that it is an even space that is open that we can place the bishop on
						
						Bishop_Class bishop_obj = new Bishop_Class("white", 'b', "bishop");
						array_2d_piece[0][newposition] = bishop_obj; // white bishop initialization
						bishop_obj = new Bishop_Class("black", 'B', "bishop");
						array_2d_piece[7][newposition] = bishop_obj; // black bishop initialization
						placed = true;
					}
					else {
						newposition = rng.nextInt(8);
					}
				}
				else 
				{ //It's not an even space and so we need to get an even position.
					newposition = rng.nextInt(8);
				}
			}
			
			placed = false;
			
			//Now we need an odd space to place the bishops on
			while(!placed) {
				if(array_2d_piece[7][newposition] == null) //This means that the position is open
				{
					if(newposition % 2 == 1) { //This tells us that it is an odd space that is open that we can place the bishop on
						
						Bishop_Class bishop_obj = new Bishop_Class("white", 'b', "bishop");
						array_2d_piece[0][newposition] = bishop_obj; // white bishop initialization
						bishop_obj = new Bishop_Class("black", 'B', "bishop");
						array_2d_piece[7][newposition] = bishop_obj; // black bishop initialization
						placed = true;
					}
					else {
						newposition = rng.nextInt(8);
					}
				}
				else 
				{ //It's not an odd space and so we need to get an even position.
					newposition = rng.nextInt(8);
				}
			}
			
			//Now place the rest of the pieces - knights and queens
			Knight_Class knight_obj = new Knight_Class("white", 'n', "knight");
			Knight_Class black_knight_obj = new Knight_Class("black", 'N', "knight");
			placePiece(knight_obj, black_knight_obj);
			
			knight_obj = new Knight_Class("white", 'n', "knight");
			black_knight_obj = new Knight_Class("black", 'N', "knight");
			placePiece(knight_obj, black_knight_obj);
			
			Queen_Class queen_obj = new Queen_Class("white", 'q', "queen");
			Queen_Class black_queen_obj = new Queen_Class("black", 'Q', "queen");
			placePiece(queen_obj, black_queen_obj);
			
			
		}

	}
	
	private void placePiece(Piece_Class white_piece, Piece_Class black_piece)
	{
		Random rng = new Random();
		int newposition = rng.nextInt(8);
		boolean placed = false;
		
		while(!placed)
		{
			if(array_2d_piece[0][newposition] == null)
			{
				array_2d_piece[0][newposition] = white_piece;
				array_2d_piece[7][newposition] = black_piece;
				placed = true;
			}
			else {
				newposition = rng.nextInt(8);
			}
		}
		
	}

	public Piece_Class get_loc_piece(int p, int q) { // method of location piece object to finding

		return array_2d_piece[p][q];
	}

	public Piece_Class[][] get_board() { // get chess board when called

		return array_2d_piece;
	}

	public void show_board() { // show current board function
		System.out.println("  0  1  2  3  4  5  6  7");

		for (int i = 0; i < 8; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 8; j++) {
				if (array_2d_piece[i][j] != null) {
					System.out.printf(array_2d_piece[i][j].symbol + "  ");
				} else {
					System.out.printf('-' + "  ");
				}
			}
			System.out.println();
			;
		}
	}

	public void set_piece(int c, int d, Piece_Class type) // set particular location in board with piece type
	{
		array_2d_piece[c][d] = type;
	}

	public void set_player(String ply) // method for set player
	{
		player_color = ply;

	}

	public String get_player() { // method for get player

		return player_color;
	}

	public static void change_turn() { // method for changing turn of user

		if (player_color == "white") {
			player_color = "black";
		} else if (player_color == "black") {
			player_color = "white";
		}
	}

} // end board class
