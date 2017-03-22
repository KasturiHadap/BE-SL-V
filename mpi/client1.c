#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "mpi.h"

int main( int argc, char **argv )
{
 MPI_Comm server;
 MPI_Status status;
 char port_name[MPI_MAX_PORT_NAME],str[50],ch;
 int i, tag,again;
 if (argc < 2)
 {
  fprintf(stderr, "server port name required.\n");
  exit(EXIT_FAILURE);
 }
 MPI_Init(&argc, &argv);
 strcpy(port_name, argv[1]);
 MPI_Comm_connect(port_name, MPI_INFO_NULL, 0, MPI_COMM_WORLD, &server);

 // accept input string
 printf("\nEnter the string :\n");
 scanf("%s",str);
 
 //send string to server (character by character)
 for (i = 0; i < strlen(str); i++)
 {
  if(str[i]!='\0')
  ch=str[i];
  tag=2;
  MPI_Send(&ch, 1, MPI_CHAR, 0, tag, server);
 }
 
 // done sending string to the server
 MPI_Send(&i, 0, MPI_INT, 0, 1, server);
 
 // Receive the reversed string from server and display it
 i=0;
 again=1;
 while (again)
 {
  MPI_Recv(&ch, 1, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, server, &status);
  switch (status.MPI_TAG)
  {
   case 2:
     str[i]=ch;
     i++;
     break;
   case 1:
     again=0;
     break;
  }
 }
 
 printf("\nReversed string is : %s\n\n",str);
 MPI_Comm_disconnect(&server);
 MPI_Finalize();
 return 0;

}
/*
student@IT19:~$ cd mpi
student@IT19:~/mpi$ mpirun -np 1 client1 '2379612160.0;tcp://172.16.0.136:56391+2379612161.0;tcp://172.16.0.136:37464:300'

Enter the string :
kasturi

Reversed string is : irutsak

student@IT19:~/mpi$ 


*/
