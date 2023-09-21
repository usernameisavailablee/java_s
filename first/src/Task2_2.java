
import mpi.MPI;

import java.lang.reflect.Array;

public class Task2_2 {
    public static void Start(String args[]){
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int[] buf = {0};

        if (rank == 0) {
            MPI.COMM_WORLD.Isend(buf, 0,1, MPI.INT, 1, 0).Wait();
            MPI.COMM_WORLD.Irecv(buf, 0,1, MPI.INT, size-1, 0).Wait();
            System.out.println(buf[0]);
        } else {
            MPI.COMM_WORLD.Irecv(buf, 0,1, MPI.INT, rank-1, 0).Wait();
            int[] a = {rank+buf[0]};
            MPI.COMM_WORLD.Isend(a, 0,1, MPI.INT, (rank+1)%size, 0).Wait();
        }



        MPI.Finalize();
    }
}
