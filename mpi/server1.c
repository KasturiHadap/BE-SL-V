#include <stdlib.h> 
#include <stdio.h>
#include "mpi.h"
#include<string.h>
int main(int argc, char **argv)
{
 MPI_Comm client;
 MPI_Status status;
 char port_name[MPI_MAX_PORT_NAME],str[50],ch,temp;
 int size, again, i,j;
 MPI_Init(&argc, &argv);
 MPI_Comm_size(MPI_COMM_WORLD, &size);
 if (size != 1)
 {
  fprintf(stderr, "Server too big");
  exit(EXIT_FAILURE);
 }
 MPI_Open_port(MPI_INFO_NULL, port_name);
 printf("Server available at port: %s\n", port_name);
 i=0;
 while (1)
 {
  MPI_Comm_accept(port_name, MPI_INFO_NULL, 0, MPI_COMM_WORLD, &client);
  again = 1;
  while (again)
  {
   MPI_Recv(&ch, 1, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, client, &status);
   switch (status.MPI_TAG)
   {
    case 0:
      MPI_Comm_free(&client);
      MPI_Close_port(port_name);
      MPI_Finalize();
      return 0;
    case 1:
      printf("\nReceived String: %s\n",str);
      // reverse the string
      i = 0;
      j = strlen(str) - 1;
      while (i < j)
      {
       temp = str[i];
       str[i] = str[j];
       str[j] = temp;
       i++;
       j--;
      }

      printf("\nReversed string is : %s\n",str);

      // send the reversed string to client (character by character)
      for (i = 0; i < strlen(str); i++)
      {
       ch=str[i];
       MPI_Send(&ch, 1, MPI_CHAR, 0, 2, client);
      }
     
      //send tag=1 to indicate end of string
      MPI_Send(&ch, 1, MPI_CHAR, 0, 1, client);
      MPI_Comm_disconnect(&client);
      again = 0;
      strcpy(str,"");
      i=0;
      break;
    case 2:
      printf("Received character: %c\n", ch);
      str[i]=ch;
      i++;
      // add null character at the end of string
      str[i]='\0';
      break;
    default:
      /* Unexpected message type */
      MPI_Abort(MPI_COMM_WORLD, 1);
   }
  }
 }

}
/*
student@IT19:~$ cd mpi/
student@IT19:~/mpi$ mpicc server1.c -o server1
student@IT19:~/mpi$ mpicc client1.c -o client1
student@IT19:~/mpi$ mpirun -np 1 server1
Server available at port: 2379612160.0;tcp://172.16.0.136:56391+2379612161.0;tcp://172.16.0.136:37464:300
Received character: k
Received character: a
Received character: s
Received character: t
Received character: u
Received character: r
Received character: i

Received String: kasturi

Reversed string is : irutsak


*/
