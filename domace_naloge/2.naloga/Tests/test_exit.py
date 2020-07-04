import pexpect
 

def test_exit_use():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")

        baza.send("exit")
        baza.expect("Bye")
        print "PASSED\ttest_exit_use"

    except:
        print "FAILED\ttest_exit_use"

    finally:
        baza.kill()



if __name__ == "__main__":
    test_exit_use()

