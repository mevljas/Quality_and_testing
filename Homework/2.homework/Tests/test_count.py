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

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("3105940500232")
        baza.expect("add> NAME:")
        baza.send("Janez Alberta")
        baza.expect("add> SURNAME:")
        baza.send("Novaka")
        baza.expect("add> AGE:")
        baza.send("12")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("count")
        baza.expect(">> No. of patients: 2")
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("3105940502232")
        baza.expect("add> NAME:")
        baza.send("Janez Albertaa")
        baza.expect("add> SURNAME:")
        baza.send("Novakaa")
        baza.expect("add> AGE:")
        baza.send("14")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("count")
        baza.expect(">> No. of patients: 3")
        baza.expect("command>")

        baza.send("remove 3105940500232")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("count")
        baza.expect(">> No. of patients: 2")
        baza.expect("command>")



        baza.send("exit")
        baza.expect("Bye")

        print "PASSED\ttest_count"

    except:
        print "FAILED\ttest_count"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_print()

