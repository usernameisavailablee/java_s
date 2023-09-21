
import mpi.*;
public class Task1 {
    public static void Start(String args[]){
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        System.out.println(rank);
        int size = MPI.COMM_WORLD.Size();

        int [] message = {1};
        if(rank % 2 == 0){
            if(rank + 1 != size){
                MPI.COMM_WORLD.Send(message,0,1,MPI.INT,rank+1,0);
            }
        } else {
            MPI.COMM_WORLD.Recv(message,0,1,MPI.INT,rank-1,0);
            System.out.println("Received");
        }
        MPI.Finalize();
    }
}




