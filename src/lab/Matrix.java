package lab;
public class Matrix{
	private int[][]  matrixData;	// integer array to store integer data
	private int    rowsNum;	// number of rows
	private int    colsNum;	// number of columns

	 //function created to help aid in methods below
	 public int matCols() { 
	       	return colsNum;
	 }     	
	 public int matRows(){ 
	        return rowsNum;
	 }
	 
	//constructor1
	public Matrix(int row, int col){
		rowsNum = row; //Initializes private variable rowsNum with row
		colsNum = col;	//Initializes private variable colsNum with col
		if (row <= 0) {
			rowsNum = 3;
		}
		if (col <= 0) {
			colsNum = 3;
		}
        matrixData = new int[rowsNum][colsNum]; // Creates a new Matrix with dimension providede in parameters
	}//end first constructor


	 // constructor2
	public Matrix(int[][] table){
		rowsNum = table.length; //obtains length for rows
		colsNum = table[0].length; //obtains length for cols
		matrixData = new int[rowsNum][colsNum]; // allocates memory for the 2D array
		for (int count = 0; count < rowsNum; count++)
			for(int count2 = 0; count2 < colsNum; count2++)
				matrixData[count][count2] = table[count][count2]; //sets matrix at count and count 2 index with table values
	}//end second constructor


	
	
	//method 1
	public int getElement(int i, int j) throws IndexOutOfBoundsException{
		if (i >= 0 && i < rowsNum) {
			if (j >= 0 && j<colsNum) {
				return matrixData[i][j]; //retrieves element if it is a valid indexx
			}
			else {
				throw new IndexOutOfBoundsException("Invalid indexes"); //if not it throws this exception
			}
		}
		else {
			throw new IndexOutOfBoundsException("Invalid indexes"); //if not it throws this exception
		}
	}//end method 1

	
	//method 2
	public boolean setElement(int  x, int i, int j){
		if (i >= 0 && i < rowsNum) {
			if (j >= 0 && j<colsNum) {
				matrixData[i][j] = x; //it finds the value of x, within the Matrix
			    return true;
			}
			else {
				return false; //if the value is not located it returns false
				}
		}
		 else{
			 return false; //if the value is not located it returns false 
         }
	}//end method 2
	
	
	

	//method 3
	public Matrix copy(){ 
		return new Matrix(matrixData); //method returns a deep copy of the original matrix
	}//end method 3
	
	

	//method4
	public void addTo(Matrix m) throws ArithmeticException{
		if ((m.matRows() == matRows()) && (m.matCols() == matCols())) {
			for (int count = 0; count < rowsNum; count++){
                for (int count2 = 0; count2 < colsNum; count2++){
                    matrixData[count][count2] += m.getElement(count,count2);
                    //iterates through values, and adds value of matrix m to matrixData
                }
			}
		} 
		else {
			throw new ArithmeticException("Invalid operation"); //if above conditions are not met this exception is thrown
		}

	}//end method 4

	
	//method 5
	public Matrix subMatrix(int i, int j) throws ArithmeticException
	{
		Matrix subMat = new Matrix(i,j); //creates new matrix
		if (i>0 && i<rowsNum) {
			if(j>0 && j<colsNum){
				for (int count = 0; count <= i; count++) {
					for (int count2 = 0; count2 <=j; count2++) {
						subMat.setElement((this.getElement(count, count2)), count, count2);
					}// if the values of i and j are within the index of the matrix, it creates subMat
				}
				return subMat;
			}
			else {
				throw new ArithmeticException("Submatrix not defined"); //if conditions are not met this exception is thrown
			}
		}
		else {
			throw new ArithmeticException("Submatrix not defined"); //if conditions are not met this exception is thrown
		}
	}// end method 5
	
	
	//method 6
	// Anytime the row is greater than the column, the int should be zero. This method checks for just that
	// and returns true or false accordingly
	
	public boolean isUpperTr() {
		for (int count = 1; count < rowsNum; count++) {
            for (int count2 = 0; count2 < count; count2++) { 
            	//checks only upper triangular values
            	if (matrixData[count][count2] != 0) {
                    return false;
                }
            }
	      }
    return true;
	}


	
    // method 7
    public static Matrix sum(Matrix[] matArray) throws ArithmeticException
    {
    	
    	int methodRows = matArray[0].matRows();
        int methodCols = matArray[0].matCols();
        Matrix sumMat = new Matrix(methodRows, methodCols); //creates new matrix with desired rows and cols values
        int count = 0;
        while (count < matArray.length){
            if (matArray[count].matRows() != methodRows){
                throw new ArithmeticException ("Invalid Matrix Dimensions"); //if number of rows are not the same throws exception
            }
            if (matArray[count].matCols() != methodCols){
            	throw new ArithmeticException ("Invalid Matrix Dimensions"); //if number of cols are not the same throws exception
            }
            count++;
        }
        for (int count1 = 0; count1 < matArray.length; count1++){
            sumMat.addTo(matArray[count1]); // adds matArray to sumMat
        }
        return sumMat;
    } // end method 7


    //method 8
	public String toString(  )
	{
		/* returns a string representing the matrix,
		   with each row on a line, and the elements in each row being separated by 2 blank spaces. */

		String output = new String(); // creates an empty string
        	for(int i = 0; i < rowsNum; i++){
        		for(int j = 0; j < colsNum; j++){
        			output += matrixData[i][j] + "  ";
        		}
        	output += "\n";
        	}
       		 return output;

	}//end method 8
	
}//end class Matrix
