import pexpect

def test_bst_save_restore():
    baza = pexpect.pexpect()
 
    try:
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("2111935500138")
        baza.expect("add> NAME:")
        baza.send("Boris")
        baza.expect("add> SURNAME:")
        baza.send("Anderlic")
        baza.expect("add> AGE:")
        baza.send("85")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("3105940500232")
        baza.expect("add> NAME:")
        baza.send("Jan Vid")
        baza.expect("add> SURNAME:")
        baza.send("Novak")
        baza.expect("add> AGE:")
        baza.send("80")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("1310945505091")
        baza.expect("add> NAME:")
        baza.send("Marija")
        baza.expect("add> SURNAME:")
        baza.send("Svet")
        baza.expect("add> AGE:")
        baza.send("75")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("print")
        baza.expect(">>Number of patients: 3")
        baza.expect("\t2111935500138 | Anderlic, Boris | 85")
        baza.expect("\t3105940500232 | Novak, Jan Vid | 80")
        baza.expect("\t1310945505091 | Svet, Marija | 75")
        baza.expect("")
        baza.expect("command>")

        baza.send("count")
        baza.expect(">> No. of patients: 3")
        baza.expect("command>")

        baza.send("save test.bin")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("reset")
        baza.expect("reset> Are you sure (y|n):")
        baza.send("y")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("print")
        baza.expect(">>Number of patients: 0")
        baza.expect("")
        baza.expect("command>")

        baza.send("count")
        baza.expect(">> No. of patients: 0")
        baza.expect("command>")

        baza.send("restore test.bin")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("print")
        baza.expect(">>Number of patients: 3")
        baza.expect("\t2111935500138 | Anderlic, Boris | 85")
        baza.expect("\t3105940500232 | Novak, Jan Vid | 80")
        baza.expect("\t1310945505091 | Svet, Marija | 75")
        baza.expect("")
        baza.expect("command>")

        baza.send("count")
        baza.expect(">> No. of patients: 3")
        baza.expect("command>")


        print "PASSED\ttest_save_restore"

    except:
        print "FAILED\ttest_save_restore"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_save_restore()

