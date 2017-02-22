#include <stdio.h>

#include <mpi.h>

main(int argc, char **argv)

{

	int ierr, num_procs, my_id;
	ierr = MPI_Init(&argc, &argv);
	/* find out MY process ID, and how many processes were started. */
	ierr = MPI_Comm_rank(MPI_COMM_WORLD, &my_id);
	ierr = MPI_Comm_size(MPI_COMM_WORLD, &num_procs);
	printf("Hello world! I’m process %i out of %iprocesses\n",my_id,num_procs);
	ierr = MPI_Finalize();
}

/*
Output:
student@IT19:~/MPI27$ mpicc hello1.c -o hello1
student@IT19:~/MPI27$ mpirun -np 5 hello1
Hello world! I’m process 0 out of 5processes
Hello world! I’m process 1 out of 5processes
Hello world! I’m process 3 out of 5processes
Hello world! I’m process 2 out of 5processes
Hello world! I’m process 4 out of 5processes
student@IT19:~/MPI27$ 
*/
