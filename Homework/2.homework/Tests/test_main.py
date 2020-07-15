import pexpect
 

def test_bst_print():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")

        baza.send("test")
        baza.expect(">> Invalid command")
        baza.expect("command>")

        baza.send("exit")
        baza.expect("Bye")

        print "PASSED\ttest_main"

    except:
        print "FAILED\ttest_main"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_print()

