/**
 * The automated testing prototype works by virtue of three components:
 *
 * > .gitlab-ci.yml
 * > test.bat
 * > gitlab-runner.exe
 *
 *  .gitlab-ci.yml specifies the test suite
 *  test.bat executes things
 *  gitlab-runner is the main entity in the background that automates running of the tests
 *
 * Everytime we check in things from your IDE to GitLab, test.bat will be run, and the CI/DI pipeline will hold the job, and run it in the pipeline.
 *
 * If you have any questions, feel free to shoot me a message. I'll continually maintain and improve this subsystem.
 */
public class autotesting_prototype {

    /**
     * Author: Albert Yu
     *
     * Class created to test automated testing.  Initial prototype.
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Hello world!");

    }

}
