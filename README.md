# 0hh1-solver
Solver for 0hh1 logic puzzles [http://0hh1.com]
* Solution based solely on constraints without backtracking
* Supports verbose mode to provide details of effectiveness of constraints on a given puzzle

* Puzzle input
  * `-` represents an empty cell
  * `b` represents a blue cell
  * `r` represents a red cell

Sample 6x6 puzzle: `-r-r-b---b-------b-b--------bb----b-`

**Usage**
```
java com.srrmlwn.puzzles._0hh1.Solver [-v] <puzzle>
```

**Non-verbose mode**
```
> java com.srrmlwn.puzzles._0hh1.Solver  -r-r-b---b-------b-b--------bb----b-
Input:
-r-r-b
---b--
-----b
-b----
----bb
----b-

Output:
brbrrb
brbbrr
rbrrbb
bbrbrr
rrbrbb
rbrbbr
```

**Verbose mode**
```
> java com.srrmlwn.puzzles._0hh1.Solver -v -r-r-b---b-------b-b--------bb----b-
Input:
-r-r-b
---b--
-----b
-b----
----bb
----b-

Output:
brbrrb
brbbrr
rbrrbb
bbrbrr
rrbrbb
rbrbbr

Constraints Summary:
Number of empty cells in input: 27
+ Row/Column should have equal number of reds and blues: 14/27 (51.85%) cells solved.
+ Three sequential cells cannot have the same color: 11/27 (40.74%) cells solved.
+ No two rows can be identical: 0/27 (.00%) cells solved.
+ No two columns can be identical: 2/27 (7.41%) cells solved.
```