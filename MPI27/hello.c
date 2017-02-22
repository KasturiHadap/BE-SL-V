#include <stdio.h>
#include <mpi.h>

main(int argc, char **argv)
{
	int ierr;
	ierr = MPI_Init(&argc, &argv);
	printf("Hello world\n");
	ierr = MPI_Finalize();
}

/*
Output:
student@IT19:~/MPI27$ mpicc hello.c -o hello
student@IT19:~/MPI27$ mpirun -np 5 hello
Hello world
Hello world
Hello world
Hello world
Hello world
student@IT19:~/MPI27$
*/
