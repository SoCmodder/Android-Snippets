public class DividerItemDecoration extends RecyclerView.ItemDecoration
{
    private Drawable divider;

    public DividerItemDecoration(Drawable divider)
    {
        this.divider = divider;
    }


    /**
     * We first calculate the left and right bounds of the parent RecyclerView, as they will be the
     * left and right bounds of our divider between each child view. We then have to calculate the
     * location of the top and bottom of each divider, as they’re unique for each child view.
     *
     * Once we have these bounds calculated, we’re able to set the bounds of each divider
     * and draw it on the screen.
     *
     * @param canvas
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state)
    {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount - 1; i++)
        {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + divider.getIntrinsicHeight();

            divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            divider.draw(canvas);
        }
    }


    /**
     * In getItemOffsets, we’re changing the shape of the Rect outRect, which determines the amount
     * of padding on each side of the the list item. By default, this padding is 0. Here, we change
     * the top field of outRect to the height of our divider so that it has room to be drawn without
     * overlapping any other views.
     *
     * Note that we skip this action for the first item in the list
     * so that we don’t draw an offset above the RecyclerView.
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0)
        {
            return;
        }

        outRect.top = divider.getIntrinsicHeight();
    }
}