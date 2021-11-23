package test;


import com.complex.entity.Core;
import com.complex.entity.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static test.TestHelper.captureOutput;

class CoreTest {
    private Core core;
    private Job job;
    private long time;




    @Test
    public void testProcessNullJob() throws Exception {

        captureOutput( new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {

                // 1, 3, 8
                core.assignJob(time, null);
                core.process(time);
                String strNullResult = "Error!No job in this core!";
                String output = outContent.toString().trim();
                assertTrue(output.contains(strNullResult));


            }
        });
    }

    @Test
    public void testProcessJob() throws Exception {

        captureOutput( new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {
                //1, 2, 5, 6 ,8
                core.assignJob(time, job);
                core.process(time);
                String strResult = "jobSize:0.0";
                String output = outContent.toString().trim();
                assertTrue(output.contains(strResult));

            }
        });

    }

    @Test
    public void testProcessJob2() throws Exception {

        captureOutput(new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {

                //1,2,4,2, 5,6,8
                job.setJobSize(10);
                core.assignJob(time, job);
                core.process(time);
                String strResult = "jobSize:10.0";
                String output = outContent.toString().trim();
                assertTrue(output.contains(strResult));

            }
        });

    }
    @Test
    public void testProcessJob3() throws Exception {

        captureOutput( new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {

                //1,2,4,2,5,7,8
                job.setJobSize(10);
                core.assignJob(time, job);
                core.process(time);
                String strResult = "jobSize:10.0";
                String output = outContent.toString().trim();
                assertTrue(output.contains(strResult));

            }
        });
    }




    @BeforeEach
    void setUp() {
        job = new Job(0);
        core = new Core();
        time=System.currentTimeMillis()/1000;



    }
// Process tests









    @Test
    void getJob() {
        core.assignJob(time, job);
        Job newJob = core.getJob();
        assertNotEquals(newJob, null);
    }

    @Test
    void getJobNull() {
        Job newJob = core.getJob();
        assertEquals(newJob, null);
    }





    @Test
    public void testAssignNullJob() throws Exception {

        captureOutput( new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {



                core.assignJob(time, null);
                String strResult = "Error!No job in this core!";
                String output = outContent.toString().trim();
                assertTrue(output.contains(strResult));

            }
        });
    }


    @Test
    public void testAssignJob() throws Exception {

        captureOutput( new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {

                Job newJob = new Job(10);

                core.assignJob(time, newJob);
                core.assignJob(time, newJob);
                String strResult = "Already has a job!";
                String output = outContent.toString().trim();
                assertTrue(output.contains(strResult));

            }
        });
    }


    @Test
    public void testAssignJob2() throws Exception {

        captureOutput( new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {

                Job newJob = new Job(10);

                core.assignJob(time, newJob);

                String strResult = "Already has a job!";
                String output = outContent.toString().trim();
                assertFalse(output.contains(strResult));

            }
        });
    }
    @Test
    public void testAssignJob3() throws Exception {

        captureOutput( new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {

                Job newJob = new Job(10);

                core.assignJob(time, newJob);

                String strResult = "Error!No job in this core!\"";
                String output = outContent.toString().trim();
                assertFalse(output.contains(strResult));

            }
        });
    }

    @Test
    public void testAssignJob4(){
        Job newJob = new Job(10);
        core.assignJob(time, newJob);
        assertEquals(time, newJob.getMarkStart());

    }
    @Test
    public void testRemoveNullJob() throws Exception {

        captureOutput( new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {


                core.removeJob(time, null);
                String strResult = "Error!No job in this core!";
                String output = outContent.toString().trim();
                assertTrue(output.contains(strResult));

            }
        });
    }
    @Test
    public void testRemoveJob() throws  Exception{
        job.setJobSize(10);
        double finishTime=time+job.getJobSize()/2;

        core.removeJob(time, job);

        assertEquals(finishTime, job.getMarkFinish());
    }
}
