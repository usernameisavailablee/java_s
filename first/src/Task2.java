
import mpi.MPI;

import java.lang.reflect.Array;

public class Task2 {
    public static void Start(String args[]){
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int[] buf = {0};

        if (rank == 0) {
            MPI.COMM_WORLD.Send(buf, 0,1, MPI.INT, 1, 0);
            MPI.COMM_WORLD.Recv(buf, 0,1, MPI.INT, size-1, 0);
            System.out.println(buf[0]);
        } else {
            MPI.COMM_WORLD.Recv(buf, 0,1, MPI.INT, rank-1, 0);
            int[] a = {rank+buf[0]};
            MPI.COMM_WORLD.Send(a, 0,1, MPI.INT, (rank+1)%size, 0);
        }



        MPI.Finalize();
    }
}
