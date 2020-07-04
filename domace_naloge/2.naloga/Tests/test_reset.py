import pexpect
 

def test_bst_print():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("3105940500232")
        baza.expect("add> NAME:")
        baza.send("Janez Albert")
        baza.expect("add> SURNAME:")
        baza.send("Novak")
        baza.expect("add> AGE:")
        baza.send("80")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("count")
        baza.expect(">> No. of patients: 1")
        baza.expect("command>")


        baza.send("reset")
        baza.expect("reset> Are you sure (y|n):")
        baza.send("y")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("count")
        baza.expect(">> No. of patients: 0")
        baza.expect("command>")

        baza.send("exit")
        baza.expect("Bye")

        print "PASSED\ttest_reset"

    except:
        print "FAILED\ttest_reset"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_print()

